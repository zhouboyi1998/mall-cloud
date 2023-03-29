package com.cafe.order.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * @Description: 订单明细表实体模型
 */
@ApiModel(value = "OrderDetail", description = "订单明细表实体模型")
@TableName("mall_order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单明细ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "SKU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long skuId;

    @ApiModelProperty(value = "SKU 名称快照")
    private String skuName;

    @ApiModelProperty(value = "SKU 主图快照")
    private String skuImage;

    @ApiModelProperty(value = "SKU 价格快照")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "SKU 购买数量")
    private Integer skuCount;

    @ApiModelProperty(value = "订单明细实际支付金额")
    private BigDecimal detailAmount;

    @ApiModelProperty(value = "状态: 0 未完成, 1 已完成")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public OrderDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderDetail setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getShopId() {
        return shopId;
    }

    public OrderDetail setShopId(Long shopId) {
        this.shopId = shopId;
        return this;
    }

    public Long getSkuId() {
        return skuId;
    }

    public OrderDetail setSkuId(Long skuId) {
        this.skuId = skuId;
        return this;
    }

    public String getSkuName() {
        return skuName;
    }

    public OrderDetail setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public OrderDetail setSkuImage(String skuImage) {
        this.skuImage = skuImage;
        return this;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public OrderDetail setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
        return this;
    }

    public Integer getSkuCount() {
        return skuCount;
    }

    public OrderDetail setSkuCount(Integer skuCount) {
        this.skuCount = skuCount;
        return this;
    }

    public BigDecimal getDetailAmount() {
        return detailAmount;
    }

    public OrderDetail setDetailAmount(BigDecimal detailAmount) {
        this.detailAmount = detailAmount;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderDetail setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public OrderDetail setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public OrderDetail setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public OrderDetail setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
            "id=" + id +
            ", orderId=" + orderId +
            ", shopId=" + shopId +
            ", skuId=" + skuId +
            ", skuName=" + skuName +
            ", skuImage=" + skuImage +
            ", skuPrice=" + skuPrice +
            ", skuCount=" + skuCount +
            ", detailAmount=" + detailAmount +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            "}";
    }
}
