package com.cafe.search.elasticsearch.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.model
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:42
 * @Description: ElasticSearch 商品实体模型
 */
@ApiModel(value = "Goods", description = "ElasticSearch 商品实体模型")
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

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "库存单位")
    private String unit;

    @ApiModelProperty(value = "销量")
    private Integer saleVolume;

    @ApiModelProperty(value = "SKU 图片")
    private String image;

    @ApiModelProperty(value = "SKU 图片列表")
    private String imageList;

    @ApiModelProperty(value = "SKU 视频")
    private String video;

    @ApiModelProperty(value = "SKU 规格")
    private String specification;

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime launchTime;

    @ApiModelProperty(value = "品牌ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long brandId;

    @ApiModelProperty(value = "分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "SPU 说明")
    private String caption;

    @ApiModelProperty(value = "SPU 详细介绍")
    private String intro;

    @ApiModelProperty(value = "评论数")
    private Integer commentVolume;

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

    public String getUnit() {
        return unit;
    }

    public Goods setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Integer getSaleVolume() {
        return saleVolume;
    }

    public Goods setSaleVolume(Integer saleVolume) {
        this.saleVolume = saleVolume;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Goods setImage(String image) {
        this.image = image;
        return this;
    }

    public String getImageList() {
        return imageList;
    }

    public Goods setImageList(String imageList) {
        this.imageList = imageList;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public Goods setVideo(String video) {
        this.video = video;
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

    public Long getShopId() {
        return shopId;
    }

    public Goods setShopId(Long shopId) {
        this.shopId = shopId;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Goods setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getIntro() {
        return intro;
    }

    public Goods setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    public Integer getCommentVolume() {
        return commentVolume;
    }

    public Goods setCommentVolume(Integer commentVolume) {
        this.commentVolume = commentVolume;
        return this;
    }

    @Override
    public String toString() {
        return "Goods{" +
            "id=" + id +
            ", skuName='" + skuName +
            ", spuId=" + spuId +
            ", originalPrice=" + originalPrice +
            ", discountPrice=" + discountPrice +
            ", seckillPrice=" + seckillPrice +
            ", stock=" + stock +
            ", unit=" + unit +
            ", saleVolume=" + saleVolume +
            ", image='" + image +
            ", imageList='" + imageList +
            ", video='" + video +
            ", specification='" + specification +
            ", launchTime=" + launchTime +
            ", brandId=" + brandId +
            ", categoryId=" + categoryId +
            ", shopId=" + shopId +
            ", caption='" + caption +
            ", intro='" + intro +
            ", commentVolume=" + commentVolume +
            '}';
    }
}
