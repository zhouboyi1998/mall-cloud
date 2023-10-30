package com.cafe.center.order.service.impl;

import com.cafe.center.order.service.OrderCenterService;
import com.cafe.common.constant.pool.BigDecimalConstant;
import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.core.result.Result;
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
import com.cafe.system.bo.AreaDetail;
import com.cafe.system.feign.AreaFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    public Result<Object> submit(Long addressId, Integer channel, Integer invoice, List<CartVO> cartVOList) {
        // ---------- step.1 确认商品状态 ----------

        // 获取 SKU 主键列表
        List<Long> skuIds = cartVOList.stream().map(CartVO::getSkuId).collect(Collectors.toList());
        // 查询未上架的 SKU
        List<Sku> unlisted = Optional.ofNullable(skuFeign.unlisted(skuIds))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());
        // 如果存在未上架的 SKU, 终止提交
        if (unlisted.size() > 0) {
            return Result.status(HttpStatusEnum.UNLISTED).data(unlisted);
        }

        // ---------- step.2 查询收货地址 ----------

        // 根据id查询收货地址
        Address address = addressFeign.one(addressId).getBody();
        // 如果收货地址不存在, 终止提交
        if (Objects.isNull(address)) {
            return Result.status(HttpStatusEnum.ADDRESS_NOT_FOUND).data(addressId.toString());
        }
        // 查询区域详细信息
        AreaDetail areaDetail = areaFeign.detail(address.getProvinceId(), address.getCityId(), address.getDistrictId()).getBody();
        Assert.notNull(areaDetail, "Unable to get AreaDetail!");

        // ---------- step.3 扣减库存 ----------

        // 扣减库存, 返回值是库存不足的 SKU 主键列表
        List<String> failIds = Optional.ofNullable(stockFeign.outboundBatch(cartVOList))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());
        // 如果存在库存不足的 SKU, 终止提交
        if (failIds.size() > 0) {
            return Result.status(HttpStatusEnum.LOW_STOCK).data(failIds);
        }

        // ---------- step.4 生成订单 ----------

        // 查询下单购买的所有商品的信息
        List<Goods> goodsList = Optional.ofNullable(goodsFeign.list(skuIds))
            .map(ResponseEntity::getBody)
            .orElse(Collections.emptyList());

        // 计算总金额和总折扣
        AtomicReference<BigDecimal> amount = new AtomicReference<>(BigDecimal.valueOf(0));
        AtomicReference<BigDecimal> discount = new AtomicReference<>(BigDecimal.valueOf(0));
        goodsList.forEach(goods -> {
            amount.set(amount.get().add(goods.getOriginalPrice()));
            discount.set(discount.get().add(goods.getOriginalPrice().subtract(goods.getDiscountPrice())));
        });
        // 判断是否需要邮费
        BigDecimal postage = amount.get().compareTo(BigDecimalConstant.ONE_HUNDRED) >= 0 ?
            BigDecimalConstant.ZERO : BigDecimalConstant.EIGHT;
        // 计算实际支付金额
        BigDecimal payment = amount.get().subtract(discount.get()).add(postage);

        // 拼装具体地址
        String specificAddress = areaDetail.getProvinceName() + StringConstant.SPACE +
            areaDetail.getCityName() + StringConstant.SPACE +
            areaDetail.getDistrictName() + StringConstant.SPACE +
            address.getAddress();

        // 生成订单主体
        OrderVO orderVO = new OrderVO()
            .setOrderNo(idFeign.nextId().getBody())
            .setMemberId(address.getMemberId())
            .setAddress(specificAddress)
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

        // SKU 主键和购买数量映射
        Map<Long, Integer> quantityMap = cartVOList.stream()
            .collect(Collectors.toMap(CartVO::getSkuId, CartVO::getQuantity));

        // 订单明细列表
        List<OrderDetail> orderDetailList = new ArrayList<>(goodsList.size());
        goodsList.forEach(goods -> {
            BigDecimal discountPrice = goods.getDiscountPrice();
            Integer quantity = quantityMap.get(goods.getId());
            // 生成订单明细
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
        orderStateFlowFeign.save(orderVO);

        return Result.success(orderVO);
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
