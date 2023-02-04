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

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "审核: 0 未审核, 1 驳回, 2 通过")
    private Integer audit;

    @ApiModelProperty(value = "状态: 0 下架, 1 上架")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

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

    public Long getShopId() {
        return shopId;
    }

    public Spu setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Integer getCommentVolume() {
        return commentVolume;
    }

    public Spu setCommentVolume(Integer commentVolume) {
        this.commentVolume = commentVolume;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Spu setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getAudit() {
        return audit;
    }

    public Spu setAudit(Integer audit) {
        this.audit = audit;
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

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public Spu setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
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
            ", shopId=" + shopId +
            ", caption=" + caption +
            ", intro=" + intro +
            ", commentVolume=" + commentVolume +
            ", sort=" + sort +
            ", audit=" + audit +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", auditTime=" + auditTime +
            ", launchTime=" + launchTime +
            ", deleted=" + deleted +
            "}";
    }
}
