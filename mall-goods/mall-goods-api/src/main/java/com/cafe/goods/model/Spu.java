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
 * @Description: 标准化产品单元实体模型
 */
@ApiModel(value = "Spu", description = "标准化产品单元实体模型")
@TableName("mall_spu")
public class Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SPU ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "SPU 名称")
    private String spuName;

    @ApiModelProperty(value = "品牌 ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long brandId;

    @ApiModelProperty(value = "三级分类 ID")
    @JsonSerialize(using = ToStringSerializer.class)
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
    private Integer commentCount;

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

    public Spu setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSpuName() {
        return spuName;
    }

    public Spu setSpuName(String spuName) {
        this.spuName = spuName;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Spu setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Spu setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Spu setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public Spu setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public Spu setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
        return this;
    }

    public String getSpuImage() {
        return spuImage;
    }

    public Spu setSpuImage(String spuImage) {
        this.spuImage = spuImage;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Spu setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Spu setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getIntro() {
        return intro;
    }

    public Spu setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Spu setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public Spu setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Spu setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Spu setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Spu setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public Spu setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Spu setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
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
