package com.cafe.center.order.service.impl;

import com.cafe.center.order.service.OrderCenterService;
import com.cafe.common.constant.pool.BigDecimalConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.core.exception.BusinessException;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.goods.bo.Goods;
import com.cafe.goods.feign.GoodsFeign;
import com.cafe.goods.feign.SkuFeign;
import com.cafe.goods.model.Sku;
import com.cafe.id.feign.IDFeign;
import com.cafe.member.feign.AddressFeign;
import com.cafe.member.model.Address;
import com.cafe.merchant.feign.StockFeign;
import com.cafe.merchant.vo.CartVO;
import com.cafe.order.feign.OrderStateFlowFeign;
import com.cafe.order.model.OrderDetail;
import com.cafe.order.vo.OrderVO;
import com.cafe.system.dto.AreaDTO;
import com.cafe.system.feign.AreaFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.center.order.service.impl
 * @Author: zhouboyi
 * @Date: 2023/10/24 17:25
 * @Description: 订单中心业务实现类
 */
@Service
public class OrderCenterServiceImpl implements OrderCenterService {

    private final SkuFeign skuFeign;

    private final AddressFeign addressFeign;

    private final AreaFeign areaFeign;

    private final StockFeign stockFeign;

    private final GoodsFeign goodsFeign;

    private final IDFeign idFeign;

    private final OrderStateFlowFeign orderStateFlowFeign;

    @Autowired
    public OrderCenterServiceImpl(SkuFeign skuFeign, AddressFeign addressFeign, AreaFeign areaFeign, StockFeign stockFeign, GoodsFeign goodsFeign, IDFeign idFeign, OrderStateFlowFeign orderStateFlowFeign) {
        this.skuFeign = skuFeign;
        this.addressFeign = addressFeign;
        this.areaFeign = areaFeign;
        this.stockFeign = stockFeign;
        this.goodsFeign = goodsFeign;
        this.idFeign = idFeign;
        this.orderStateFlowFeign = orderStateFlowFeign;
    }

    @Override
    public OrderVO submit(Long addressId, Integer channel, Integer invoice, List<CartVO> cartVOList) {
        // 查询下单购买的所有商品的
        List<Goods> goodsList = selectGoodsList(cartVOList);
        // 查询收货地址
        Address address = selectAddress(addressId);
        // 查询区域
        AreaDTO areaDTO = selectAreaDTO(address);
        // 扣减库存
        outboundStock(cartVOList);
        // 创建订单
        return createOrder(channel, invoice, cartVOList, goodsList, address, areaDTO);
    }

    /**
     * 查询下单购买的所有商品
     *
     * @param cartVOList
     * @return
     */
    private List<Goods> selectGoodsList(List<CartVO> cartVOList) {
        // 获取 SKU 主键列表
        List<Long> skuIds = cartVOList.stream().map(CartVO::getSkuId).collect(Collectors.toList());
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
     * @param addressId
     * @return
     */
    private Address selectAddress(Long addressId) {
        // 根据地址 ID 查询收货地址 (如果收货地址不存在, 终止订单提交)
        return Optional.ofNullable(addressFeign.one(addressId))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.ADDRESS_NOT_FOUND, addressId.toString()));
    }

    /**
     * 查询区域
     *
     * @param address
     * @return
     */
    private AreaDTO selectAreaDTO(Address address) {
        // 根据省份id、城市id、区县id获取区域 (如果区域不存在, 终止订单提交)
        return Optional.ofNullable(areaFeign.dto(address.getProvinceId(), address.getCityId(), address.getDistrictId()))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.AREA_NOT_FOUND, address));
    }

    /**
     * 扣减库存
     *
     * @param cartVOList
     */
    private void outboundStock(List<CartVO> cartVOList) {
        // SKU 出库, 返回值是库存不足的 SKU 主键列表
        List<String> failIds = Optional.ofNullable(stockFeign.outboundBatch(cartVOList))
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
     * @param channel
     * @param invoice
     * @param cartVOList
     * @param goodsList
     * @param address
     * @param areaDTO
     * @return
     */
    private OrderVO createOrder(Integer channel, Integer invoice, List<CartVO> cartVOList, List<Goods> goodsList, Address address, AreaDTO areaDTO) {
        // SKU 主键和购买数量映射
        Map<Long, Integer> quantityMap = cartVOList.stream().collect(Collectors.toMap(CartVO::getSkuId, CartVO::getQuantity));

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
        String addressSnapshot = areaDTO.getProvinceName() + StringConstant.SPACE + areaDTO.getCityName() + StringConstant.SPACE + areaDTO.getDistrictName() + StringConstant.SPACE + address.getAddress();

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
            .setChannel(channel)
            .setInvoice(invoice)
            .setStatus(IntegerConstant.ZERO);

        // 循环生成订单明细
        List<OrderDetail> orderDetailList = new ArrayList<>(goodsList.size());
        goodsList.forEach(goods -> {
            BigDecimal discountPrice = goods.getDiscountPrice();
            Integer quantity = quantityMap.get(goods.getId());
            OrderDetail orderDetail = new OrderDetail()
                .setSkuId(goods.getId())
                .setShopId(goods.getShopId())
                .setSkuName(goods.getSkuName())
                .setSkuImage(goods.getImage())
                .setSkuPrice(discountPrice)
                .setSkuQuantity(quantity)
                .setDetailAmount(discountPrice.multiply(BigDecimal.valueOf(quantity)))
                .setStatus(IntegerConstant.ZERO);
            orderDetailList.add(orderDetail);
        });
        orderVO.setOrderDetailList(orderDetailList);

        // 保存订单
        return Optional.ofNullable(orderStateFlowFeign.save(orderVO))
            .map(ResponseEntity::getBody)
            .orElseThrow(() -> new BusinessException(HttpStatusEnum.CREATE_ORDER_EXCEPTION));
    }

    @Override
    public void autoCancel(LocalDateTime now, Integer duration) {
        // 自动取消超时未支付的订单
        List<OrderDetail> orderDetailList = Optional.ofNullable(orderStateFlowFeign.autoCancel(now, duration))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());

        // 还原商品库存
        List<CartVO> cartVOList = orderDetailList.stream()
            .map(orderDetail -> new CartVO()
                .setSkuId(orderDetail.getSkuId())
                .setShopId(orderDetail.getShopId())
                .setQuantity(orderDetail.getSkuQuantity()))
            .collect(Collectors.toList());
        stockFeign.inboundBatch(cartVOList);
    }
}
