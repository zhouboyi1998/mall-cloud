package com.cafe.goods.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: Standard Product Unit 标准化产品单元 (实体类)
 */
@TableName("mall_spu")
@ApiModel(value="Spu对象", description="Standard Product Unit 标准化产品单元")
public class Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String spuName;

    @ApiModelProperty(value = "品牌 ID")
    private Integer brandId;

    @ApiModelProperty(value = "三级分类 ID")
    private Integer categoryId;

    @ApiModelProperty(value = "分类码（存储多级分类ID）")
    private String categoryCode;

    @ApiModelProperty(value = "商品原价")
    private Double originalPrice;

    @ApiModelProperty(value = "商品折扣价")
    private Double discountPrice;

    @ApiModelProperty(value = "商品秒杀价")
    private Double seckillPrice;

    @ApiModelProperty(value = "商品主图 URL")
    private String image;

    @ApiModelProperty(value = "商品评论数量")
    private Long commentCount;

    @ApiModelProperty(value = "商品说明")
    private String caption;

    @ApiModelProperty(value = "商品详情介绍")
    private String intro;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "状态：0 未审核，1 审核未通过，2 审核通过，3 上架")
    private Integer spuStatus;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除：0 未删除，1 已删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSpuStatus() {
        return spuStatus;
    }

    public void setSpuStatus(Integer spuStatus) {
        this.spuStatus = spuStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Spu{" +
        "id=" + id +
        ", spuName=" + spuName +
        ", brandId=" + brandId +
        ", categoryId=" + categoryId +
        ", categoryCode=" + categoryCode +
        ", originalPrice=" + originalPrice +
        ", discountPrice=" + discountPrice +
        ", seckillPrice=" + seckillPrice +
        ", image=" + image +
        ", commentCount=" + commentCount +
        ", caption=" + caption +
        ", intro=" + intro +
        ", sort=" + sort +
        ", spuStatus=" + spuStatus +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}";
    }
}
