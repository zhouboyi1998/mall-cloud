package com.cafe.storage.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 库存实体模型
 */
@ApiModel(value = "Stock", description = "库存实体模型")
@TableName("mall_stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "库存ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "SKU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long skuId;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "仓库ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long storageId;

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "库存单位")
    private String unit;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public Stock setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getSkuId() {
        return skuId;
    }

    public Stock setSkuId(Long skuId) {
        this.skuId = skuId;
        return this;
    }

    public Long getShopId() {
        return shopId;
    }

    public Stock setShopId(Long shopId) {
        this.shopId = shopId;
        return this;
    }

    public Long getStorageId() {
        return storageId;
    }

    public Stock setStorageId(Long storageId) {
        this.storageId = storageId;
        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public Stock setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Stock setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Stock setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Stock setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "Stock{" +
            "id=" + id +
            ", skuId=" + skuId +
            ", shopId=" + shopId +
            ", storageId=" + storageId +
            ", stock=" + stock +
            ", unit=" + unit +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
