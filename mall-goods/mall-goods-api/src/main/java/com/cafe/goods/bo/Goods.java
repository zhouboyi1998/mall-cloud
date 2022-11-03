package com.cafe.goods.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.bo
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:42
 * @Description:
 */
@ApiModel(value = "Goods对象", description = "商品")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
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
    private Long brandId;

    @ApiModelProperty(value = "分类 ID")
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public void setSkuImage(String skuImage) {
        this.skuImage = skuImage;
    }

    public String getSkuImageList() {
        return skuImageList;
    }

    public void setSkuImageList(String skuImageList) {
        this.skuImageList = skuImageList;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
