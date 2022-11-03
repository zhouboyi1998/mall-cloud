package com.cafe.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 标准化产品单元 (实体类)
 */
@ApiModel(value = "Spu对象", description = "标准化产品单元")
@TableName("mall_spu")
public class Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SPU ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "SPU 名称")
    private String spuName;

    @ApiModelProperty(value = "品牌 ID")
    private Long brandId;

    @ApiModelProperty(value = "三级分类 ID")
    private Long categoryId;

    @ApiModelProperty(value = "SPU 原价")
    private Double originalPrice;

    @ApiModelProperty(value = "SPU 折扣价")
    private Double discountPrice;

    @ApiModelProperty(value = "SPU 秒杀价")
    private Double seckillPrice;

    @ApiModelProperty(value = "SPU 主图")
    private String spuImage;

    @ApiModelProperty(value = "SPU 评论数量")
    private Long commentCount;

    @ApiModelProperty(value = "SPU 说明")
    private String caption;

    @ApiModelProperty(value = "SPU 详情介绍")
    private String intro;

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

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
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

    public String getSpuImage() {
        return spuImage;
    }

    public void setSpuImage(String spuImage) {
        this.spuImage = spuImage;
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

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
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
            ", originalPrice=" + originalPrice +
            ", discountPrice=" + discountPrice +
            ", seckillPrice=" + seckillPrice +
            ", spuImage=" + spuImage +
            ", commentCount=" + commentCount +
            ", caption=" + caption +
            ", intro=" + intro +
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
