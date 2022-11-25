package com.cafe.goods.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.bo
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:42
 * @Description: 商品业务模型
 */
@ApiModel(value = "Goods", description = "商品业务模型")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
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

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime launchTime;

    @ApiModelProperty(value = "品牌 ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long brandId;

    @ApiModelProperty(value = "分类 ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    @ApiModelProperty(value = "SPU 名称")
    private String spuName;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    public Long getId() {
        return id;
    }

    public Goods setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSkuName() {
        return skuName;
    }

    public Goods setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public Long getSpuId() {
        return spuId;
    }

    public Goods setSpuId(Long spuId) {
        this.spuId = spuId;
        return this;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Goods setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public Goods setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public Goods setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public Goods setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public Goods setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
        return this;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public Goods setSkuImage(String skuImage) {
        this.skuImage = skuImage;
        return this;
    }

    public String getSkuImageList() {
        return skuImageList;
    }

    public Goods setSkuImageList(String skuImageList) {
        this.skuImageList = skuImageList;
        return this;
    }

    public String getSpecification() {
        return specification;
    }

    public Goods setSpecification(String specification) {
        this.specification = specification;
        return this;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public Goods setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Goods setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Goods setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getSpuName() {
        return spuName;
    }

    public Goods setSpuName(String spuName) {
        this.spuName = spuName;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public Goods setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Goods setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    @Override
    public String toString() {
        return "Goods{" +
            "id=" + id +
            ", skuName='" + skuName + '\'' +
            ", spuId=" + spuId +
            ", originalPrice=" + originalPrice +
            ", discountPrice=" + discountPrice +
            ", seckillPrice=" + seckillPrice +
            ", stock=" + stock +
            ", saleCount=" + saleCount +
            ", skuImage='" + skuImage + '\'' +
            ", skuImageList='" + skuImageList + '\'' +
            ", specification='" + specification + '\'' +
            ", launchTime=" + launchTime +
            ", brandId=" + brandId +
            ", categoryId=" + categoryId +
            ", spuName='" + spuName + '\'' +
            ", brandName='" + brandName + '\'' +
            ", categoryName='" + categoryName + '\'' +
            '}';
    }
}
