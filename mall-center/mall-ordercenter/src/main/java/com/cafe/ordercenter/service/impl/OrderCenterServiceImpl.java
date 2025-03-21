package com.cafe.ordercenter.service.impl;

import com.cafe.common.constant.pool.BigDecimalConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.foundation.feign.AreaFeign;
import com.cafe.foundation.model.vo.AreaDetailVO;
import com.cafe.goods.feign.GoodsFeign;
import com.cafe.goods.feign.SkuFeign;
import com.cafe.goods.model.bo.Goods;
import com.cafe.goods.model.entity.Sku;
import com.cafe.id.feign.IDFeign;
import com.cafe.member.feign.AddressFeign;
import com.cafe.member.model.entity.Address;
import com.cafe.order.feign.OrderFlowFeign;
import com.cafe.order.model.entity.OrderItem;
import com.cafe.order.model.vo.OrderVO;
import com.cafe.ordercenter.service.OrderCenterService;
import com.cafe.ordercenter.utils.TransactionContext;
import com.cafe.starter.boot.model.exception.BusinessException;
import com.cafe.storage.feign.StockFeign;
import com.cafe.storage.model.dto.CartDTO;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter.service.impl
 * @Author: zhouboyi
 * @Date: 2023/10/24 17:25
 * @Description: 订单中心业务实现类
 */
@RequiredArgsConstructor
@Service
public class OrderCenterServiceImpl implements OrderCenterService {

    private final SkuFeign skuFeign;

    private final AddressFeign addressFeign;

    private final AreaFeign areaFeign;

    private final StockFeign stockFeign;

    private final GoodsFeign goodsFeign;

    private final IDFeign idFeign;

    private final OrderFlowFeign orderFlowFeign;

    /**
     *
     * 1. 并行查询商品和地址
     *    ├─ selectGoodsList(cartDTOList) → goodsFuture
     *    └─ selectAddress(addressId) → addressFuture
     *
     * 2. 查询区域信息（依赖地址）
     *    └─ addressFuture → selectAreaDetailVO() → areaFuture
     *
     * 3. 扣减库存（依赖商品信息）
     *    └─ goodsFuture → outboundStock(cartDTOList) → stockFuture
     *
     * 4. 创建订单（依赖所有查询结果）
     *    └─ allOf(goods, address, area) → createOrder(...) → orderFuture
     *
     * 5. 事务保证
     *    └─ stockFuture → orderFuture → finalFuture → return
     *
     * @param addressId   地址ID
     * @param cartDTOList 购物车DTO列表
     * @return
     */
    @GlobalTransactional(
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class,
            timeoutMills = 30000, // 缩短超时时间
            lockRetryInterval = 100 // 合理重试间隔
    )
    @Override
    public OrderVO submit(Long addressId, List<CartDTO> cartDTOList) {
        // 1. 并行查询商品和地址
        CompletableFuture<List<Goods>> goodsFuture = CompletableFuture.supplyAsync(
                () -> TransactionContext.bind(() -> selectGoodsList(cartDTOList)) // 绑定事务上下文
        );
        CompletableFuture<Address> addressFuture = CompletableFuture.supplyAsync(
                () -> TransactionContext.bind(() -> selectAddress(addressId))
        );

        // 2. 查询区域信息（依赖地址）
        CompletableFuture<AreaDetailVO> areaFuture = addressFuture.thenApplyAsync(address ->
                TransactionContext.bind(() -> selectAreaDetailVO(address))
        );

        // 3. 扣减库存（依赖商品查询结果）
        CompletableFuture<Void> stockFuture = goodsFuture.thenRunAsync(() ->
                TransactionContext.run(() -> { // 使用 Runnable 版本的上下文绑定
                    try {
                        outboundStock(cartDTOList);
                    } catch (Exception e) {
                        throw new RuntimeException("库存扣减失败", e);
                    }
                })
        );

        // 4. 创建订单（依赖所有查询结果和库存扣减完成）
        CompletableFuture<OrderVO> orderFuture = CompletableFuture
                .allOf(goodsFuture, addressFuture, areaFuture, stockFuture)
                .thenApplyAsync(ignored -> TransactionContext.bind(() -> createOrder(
                        cartDTOList,
                        goodsFuture.join(),
                        addressFuture.join(),
                        areaFuture.join()
                )));

        // 5. 处理结果和异常
        try {
            return orderFuture.join();
        } catch (CompletionException e) {
            throw new RuntimeException("订单提交失败", e.getCause());
        }
    }
    /**
     * 查询下单商品的详细信息
     *
     * @param cartDTOList 购物车DTO列表
     * @return 商品详细信息
     */
    private List<Goods> selectGoodsList(List<CartDTO> cartDTOList) {
        // 获取 SKU 主键列表
        List<Long> skuIds = cartDTOList.stream().map(CartDTO::getSkuId).collect(Collectors.toList());
        // 查询购买的 SKU 是否存在已下架的
        List<Sku> unlisted = Optional.ofNullable(skuFeign.unlisted(skuIds))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());
        // 如果存在下架状态的 SKU, 终止订单提交
        if (unlisted.size() > 0) {
            throw new BusinessException(HttpStatusEnum.UNLISTED, unlisted);
        }

        // 返回下单购买的所有商品的信息
        return Optional.ofNullable(goodsFeign.list(skuIds))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());
    }

    /**
     * 查询收货地址
     *
     * @param addressId 地址ID
     * @return 收货地址
     */
    private Address selectAddress(Long addressId) {
        // 根据地址 ID 查询收货地址 (如果收货地址不存在, 终止订单提交)
        return Optional.ofNullable(addressFeign.one(addressId))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.ADDRESS_NOT_FOUND, addressId.toString()));
    }

    /**
     * 查询收货地址的所属区域
     *
     * @param address 地址ID
     * @return 所属区域详情
     */
    private AreaDetailVO selectAreaDetailVO(Address address) {
        // 根据省份id、城市id、区县id获取区域详情 (如果区域不存在, 终止订单提交)
        return Optional.ofNullable(areaFeign.detail(address.getProvinceId(), address.getCityId(), address.getDistrictId()))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.AREA_NOT_FOUND, address));
    }

    /**
     * 扣减库存
     *
     * @param cartDTOList 购物车DTO列表
     */
    private void outboundStock(List<CartDTO> cartDTOList) {
        // SKU 出库, 返回值是库存不足的 SKU 主键列表
        List<String> failIds = Optional.ofNullable(stockFeign.outboundBatch(cartDTOList))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());
        // 如果存在库存不足的 SKU, 终止提交
        if (failIds.size() > 0) {
            throw new BusinessException(HttpStatusEnum.LOW_STOCK, failIds);
        }
    }

    /**
     * 创建订单
     *
     * @param cartDTOList  购物车DTO列表
     * @param goodsList    下单购买的商品列表
     * @param address      收货地址
     * @param areaDetailVO 收货区域详情
     * @return 订单信息
     */
    private OrderVO createOrder(List<CartDTO> cartDTOList, List<Goods> goodsList, Address address, AreaDetailVO areaDetailVO) {
        // SKU 主键和购买数量映射
        Map<Long, Integer> quantityMap = cartDTOList.stream().collect(Collectors.toMap(CartDTO::getSkuId, CartDTO::getQuantity));

        // 计算总金额和总折扣
        AtomicReference<BigDecimal> amount = new AtomicReference<>(BigDecimal.valueOf(0));
        AtomicReference<BigDecimal> discount = new AtomicReference<>(BigDecimal.valueOf(0));
        goodsList.forEach(goods -> {
            amount.set(amount.get().add(goods.getOriginalPrice()));
            discount.set(discount.get().add(goods.getOriginalPrice().subtract(goods.getDiscountPrice())));
        });

        // 判断是否需要邮费 (商品总金额 100 块以下收 8 块邮费)
        BigDecimal postage = amount.get().compareTo(BigDecimalConstant.ONE_HUNDRED) >= 0 ? BigDecimalConstant.ZERO : BigDecimalConstant.EIGHT;

        // 计算实际支付金额
        BigDecimal payment = amount.get().subtract(discount.get()).add(postage);

        // 拼装地址快照
        String addressSnapshot = areaDetailVO.getProvinceName() + StringConstant.SPACE + areaDetailVO.getCityName() + StringConstant.SPACE + areaDetailVO.getDistrictName() + StringConstant.SPACE + address.getAddress();

        // 生成订单主体
        OrderVO orderVO = new OrderVO()
            .setOrderNo(idFeign.nextId().getBody())
            .setMemberId(address.getMemberId())
            .setAddress(addressSnapshot)
            .setReceiver(address.getReceiver())
            .setMobile(address.getMobile())
            .setAmount(amount.get())
            .setDiscount(discount.get())
            .setCoupon(BigDecimalConstant.ZERO)
            .setPostage(postage)
            .setPayment(payment)
            .setStatus(IntegerConstant.ZERO);

        // 循环生成订单明细
        List<OrderItem> orderItemList = new ArrayList<>(goodsList.size());
        goodsList.forEach(goods -> {
            BigDecimal discountPrice = goods.getDiscountPrice();
            Integer quantity = quantityMap.get(goods.getId());
            OrderItem orderItem = new OrderItem()
                .setSkuId(goods.getId())
                .setShopId(goods.getShopId())
                .setSkuName(goods.getSkuName())
                .setSkuImage(goods.getImage())
                .setSkuPrice(discountPrice)
                .setSkuQuantity(quantity)
                .setAmount(discountPrice.multiply(BigDecimal.valueOf(quantity)))
                .setStatus(IntegerConstant.ZERO);
            orderItemList.add(orderItem);
        });
        orderVO.setOrderItemList(orderItemList);

        // 保存订单
        return Optional.ofNullable(orderFlowFeign.save(orderVO))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.CREATE_ORDER_EXCEPTION));
    }

    @Override
    public void cancel(LocalDateTime now, Integer duration) {
        // 取消超时未支付的订单
        List<OrderItem> orderItemList = Optional.ofNullable(orderFlowFeign.cancel(now, duration))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());

        // 还原商品库存
        List<CartDTO> cartDTOList = orderItemList.stream()
            .map(orderItem -> new CartDTO()
                .setSkuId(orderItem.getSkuId())
                .setShopId(orderItem.getShopId())
                .setQuantity(orderItem.getSkuQuantity()))
            .collect(Collectors.toList());
        stockFeign.inboundBatch(cartDTOList);
    }
}
