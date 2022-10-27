package com.cafe.search.solr.model;

import com.cafe.search.solr.constant.SolrConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr.model
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:42
 * @Description:
 */
@ApiModel(value = "SolrGoods对象", description = "Solr 商品对象")
@SolrDocument(collection = SolrConstant.GOODS_INDEX)
public class SolrGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    @Id
    @Field
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    @Field
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
    @Field
    private Long spuId;

    @ApiModelProperty(value = "SKU 原价")
    @Field
    private Double originalPrice;

    @ApiModelProperty(value = "SKU 折扣价")
    @Field
    private Double discountPrice;

    @ApiModelProperty(value = "SKU 秒杀价")
    @Field
    private Double seckillPrice;

    @ApiModelProperty(value = "库存数量")
    @Field
    private Integer stock;

    @ApiModelProperty(value = "销售数量")
    @Field
    private Integer saleCount;

    @ApiModelProperty(value = "SKU 主图")
    @Field
    private String image;

    @ApiModelProperty(value = "SKU 图片列表")
    @Field
    private String imageList;

    @ApiModelProperty(value = "SKU 规格")
    @Field
    private String specification;

    @ApiModelProperty(value = "品牌 ID")
    @Field
    private Long brandId;

    @ApiModelProperty(value = "分类 ID")
    @Field
    private Long categoryId;

    @ApiModelProperty(value = "SPU 名称")
    @Field
    private String spuName;

    @ApiModelProperty(value = "品牌名称")
    @Field
    private String brandName;

    @ApiModelProperty(value = "分类名称")
    @Field
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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
        return "SolrGoods{" +
            "id=" + id +
            ", skuName='" + skuName + '\'' +
            ", spuId=" + spuId +
            ", originalPrice=" + originalPrice +
            ", discountPrice=" + discountPrice +
            ", seckillPrice=" + seckillPrice +
            ", stock=" + stock +
            ", saleCount=" + saleCount +
            ", image='" + image + '\'' +
            ", imageList='" + imageList + '\'' +
            ", specification='" + specification + '\'' +
            ", brandId='" + brandId + '\'' +
            ", categoryId='" + categoryId + '\'' +
            ", spuName='" + spuName + '\'' +
            ", brandName='" + brandName + '\'' +
            ", categoryName='" + categoryName + '\'' +
            '}';
    }
}