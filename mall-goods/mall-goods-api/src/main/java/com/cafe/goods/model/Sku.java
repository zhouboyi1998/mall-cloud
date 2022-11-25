package com.cafe.goods.model;

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
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位实体模型
 */
@ApiModel(value = "Sku", description = "库存量单位实体模型")
@TableName("mall_sku")
public class Sku implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long spuId;

    @ApiModelProperty(value = "SKU 原价")
    private Double originalPrice;

    @ApiModelProperty(value = "SKU 折扣价")
    private Double discountPrice;

    @ApiModelProperty(value = "SKU 秒杀价")
    private Double seckillPrice;

    @ApiModelProperty(value = "库存数量")
    private Integer stock;

    @ApiModelProperty(value = "销售数量")
    private Integer saleCount;

    @ApiModelProperty(value = "SKU 主图")
    private String skuImage;

    @ApiModelProperty(value = "SKU 图片列表")
    private String skuImageList;

    @ApiModelProperty(value = "SKU 规格")
    private String specification;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "审核状态: 0 未审核, -1 审核未通过, 1 审核通过")
    private Integer checkStatus;

    @ApiModelProperty(value = "状态: 0 下架, 1 上架")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime launchTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public Sku setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSkuName() {
        return skuName;
    }

    public Sku setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public Long getSpuId() {
        return spuId;
    }

    public Sku setSpuId(Long spuId) {
        this.spuId = spuId;
        return this;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Sku setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public Sku setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public Sku setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public Sku setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public Sku setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
        return this;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public Sku setSkuImage(String skuImage) {
        this.skuImage = skuImage;
        return this;
    }

    public String getSkuImageList() {
        return skuImageList;
    }

    public Sku setSkuImageList(String skuImageList) {
        this.skuImageList = skuImageList;
        return this;
    }

    public String getSpecification() {
        return specification;
    }

    public Sku setSpecification(String specification) {
        this.specification = specification;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Sku setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public Sku setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Sku setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Sku setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Sku setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public Sku setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Sku setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Sku{" +
            "id=" + id +
            ", skuName=" + skuName +
            ", spuId=" + spuId +
            ", originalPrice=" + originalPrice +
            ", discountPrice=" + discountPrice +
            ", seckillPrice=" + seckillPrice +
            ", stock=" + stock +
            ", saleCount=" + saleCount +
            ", skuImage=" + skuImage +
            ", skuImageList=" + skuImageList +
            ", specification=" + specification +
            ", sort=" + sort +
            ", checkStatus=" + checkStatus +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", launchTime=" + launchTime +
            ", deleted=" + deleted +
            "}";
    }
}
