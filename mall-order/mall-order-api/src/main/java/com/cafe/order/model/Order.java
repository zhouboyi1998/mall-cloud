package com.cafe.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单实体模型
 */
@ApiModel(value = "Order", description = "订单实体模型")
@TableName("mall_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderNo;

    @ApiModelProperty(value = "会员ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberId;

    @ApiModelProperty(value = "收货地址快照")
    private String address;

    @ApiModelProperty(value = "收货人快照")
    private String receiver;

    @ApiModelProperty(value = "收货人手机号快照")
    private String mobile;

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "折扣/满减金额")
    private BigDecimal discount;

    @ApiModelProperty(value = "优惠券金额")
    private BigDecimal coupon;

    @ApiModelProperty(value = "邮费")
    private BigDecimal postage;

    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal payment;

    @ApiModelProperty(value = "支付渠道: 0 银行卡, 1 支付宝, 2 微信")
    private Integer paymentChannel;

    @ApiModelProperty(value = "发票: 0 不开发票, 1 电子发票")
    private Integer invoice;

    @ApiModelProperty(value = "状态: 0 下单, 1 支付中, 2 付款失败, 3 付款成功, 4 申请退款, 5 退款成功, 6 已发货, 7 完成, 8 取消")
    private Integer status;

    @ApiModelProperty(value = "下单时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime deliverTime;

    @ApiModelProperty(value = "完成时间")
    private LocalDateTime completionTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public Order setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Order setMemberId(Long memberId) {
        this.memberId = memberId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public Order setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Order setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Order setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Order setDiscount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public Order setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
        return this;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public Order setPostage(BigDecimal postage) {
        this.postage = postage;
        return this;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public Order setPayment(BigDecimal payment) {
        this.payment = payment;
        return this;
    }

    public Integer getPaymentChannel() {
        return paymentChannel;
    }

    public Order setPaymentChannel(Integer paymentChannel) {
        this.paymentChannel = paymentChannel;
        return this;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public Order setInvoice(Integer invoice) {
        this.invoice = invoice;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Order setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Order setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Order setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public Order setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public LocalDateTime getDeliverTime() {
        return deliverTime;
    }

    public Order setDeliverTime(LocalDateTime deliverTime) {
        this.deliverTime = deliverTime;
        return this;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public Order setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Order setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", orderNo=" + orderNo +
            ", memberId=" + memberId +
            ", address=" + address +
            ", receiver=" + receiver +
            ", mobile=" + mobile +
            ", amount=" + amount +
            ", discount=" + discount +
            ", coupon=" + coupon +
            ", postage=" + postage +
            ", payment=" + payment +
            ", paymentChannel=" + paymentChannel +
            ", invoice=" + invoice +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", paymentTime=" + paymentTime +
            ", deliverTime=" + deliverTime +
            ", completionTime=" + completionTime +
            ", deleted=" + deleted +
            "}";
    }
}
