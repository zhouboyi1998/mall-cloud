package com.cafe.ordercenter.service.impl;

import com.cafe.common.constant.model.GoodsConstant;
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
import com.cafe.order.feign.OrderFeign;
import com.cafe.order.feign.OrderFlowFeign;
import com.cafe.order.feign.OrderItemFeign;
import com.cafe.order.model.entity.OrderItem;
import com.cafe.order.model.vo.OrderVO;
import com.cafe.ordercenter.executor.ThreadPoolHolder;
import com.cafe.ordercenter.service.OrderCenterService;
import com.cafe.review.feign.GoodsReviewFeign;
import com.cafe.review.feign.OrderReviewFeign;
import com.cafe.review.model.query.GoodsReviewSaveQuery;
import com.cafe.review.model.query.OrderReviewAndGoodsReviewSaveQuery;
import com.cafe.review.model.query.OrderReviewSaveQuery;
import com.cafe.starter.boot.model.exception.BusinessException;
import com.cafe.storage.feign.StockFeign;
import com.cafe.storage.model.dto.CartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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

    private final GoodsReviewFeign goodsReviewFeign;

    private final OrderReviewFeign orderReviewFeign;

    private final OrderFeign orderFeign;

    private final OrderItemFeign orderItemFeign;

    @Override
    public OrderVO submit(Long addressId, List<CartDTO> cartDTOList) {
        // 查询下单商品的详细信息
        CompletableFuture<List<Goods>> selectGoodsListFuture = CompletableFuture.supplyAsync(() -> selectGoodsList(cartDTOList), ThreadPoolHolder.SUBMIT_ORDER_THREAD_POOL);
        // 查询收货地址
        CompletableFuture<Address> selectAddressFuture = CompletableFuture.supplyAsync(() -> selectAddress(addressId), ThreadPoolHolder.SUBMIT_ORDER_THREAD_POOL);
        // 查询收货地址的所属区域
        CompletableFuture<AreaDetailVO> selectAreaDetailVOFuture = selectAddressFuture.thenApplyAsync(this::selectAreaDetailVO, ThreadPoolHolder.SUBMIT_ORDER_THREAD_POOL);
        // 扣减库存
        CompletableFuture<Void> outboundStockFuture = CompletableFuture.runAsync(() -> outboundStock(cartDTOList), ThreadPoolHolder.SUBMIT_ORDER_THREAD_POOL);
        // 创建订单
        CompletableFuture.allOf(selectGoodsListFuture, selectAreaDetailVOFuture);
        CompletableFuture<OrderVO> createOrderFuture = CompletableFuture.supplyAsync(() -> createOrder(cartDTOList, selectGoodsListFuture.join(), selectAddressFuture.join(), selectAreaDetailVOFuture.join()), ThreadPoolHolder.SUBMIT_ORDER_THREAD_POOL);
        // 业务流程全部执行完成, 返回订单信息
        CompletableFuture.allOf(outboundStockFuture, createOrderFuture);
        return createOrderFuture.join();
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
        List<Sku> offShelveList = Optional.ofNullable(skuFeign.offShelveList(skuIds))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());
        // 如果存在下架状态的 SKU, 终止订单提交
        if (!CollectionUtils.isEmpty(offShelveList)) {
            throw new BusinessException(HttpStatusEnum.OFF_SHELVE, offShelveList);
        }

        // 返回下单购买的所有商品的信息
        return Optional.ofNullable(goodsFeign.list(GoodsConstant.QueryType.FULL, skuIds))
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
        if (!CollectionUtils.isEmpty(failIds)) {
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

    @Override
    public void review(OrderReviewAndGoodsReviewSaveQuery query) {
        // 保存商品评论
        List<GoodsReviewSaveQuery> goodsReviewSaveQueryList = query.getGoodsReviewSaveQueryList();
        goodsReviewFeign.reviewBatch(goodsReviewSaveQueryList);
        // 修改订单明细评论状态
        List<Long> orderItemIds = goodsReviewSaveQueryList.stream()
            .map(GoodsReviewSaveQuery::getOrderItemId)
            .distinct()
            .collect(Collectors.toList());
        orderItemFeign.reviewBatch(orderItemIds);

        // 保存订单评论
        List<OrderReviewSaveQuery> orderReviewSaveQueryList = query.getOrderReviewSaveQueryList();
        orderReviewFeign.reviewBatch(orderReviewSaveQueryList);
        // 修改订单评论状态
        Long orderId = orderReviewSaveQueryList.get(0).getOrderId();
        orderFeign.review(orderId);
    }
}
