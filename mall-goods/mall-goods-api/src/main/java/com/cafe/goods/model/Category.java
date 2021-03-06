package com.cafe.goods.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类 (实体类)
 */
@TableName("mall_category")
@ApiModel(value = "Category对象", description = "分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "上级分类 ID")
    private Integer parentId;

    @ApiModelProperty(value = "分类等级：1 一级分类，2 二级分类，3 三级分类")
    private Integer categoryLevel;

    @ApiModelProperty(value = "分类码（存储多级分类 ID）")
    private String categoryCode;

    @ApiModelProperty(value = "该分类下的商品数量")
    private Integer goodsCount;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "状态：0 禁用，1 正常")
    private Integer categoryStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", categoryName=" + categoryName +
            ", parentId=" + parentId +
            ", categoryLevel=" + categoryLevel +
            ", categoryCode=" + categoryCode +
            ", goodsCount=" + goodsCount +
            ", sort=" + sort +
            ", categoryStatus=" + categoryStatus +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            "}";
    }
}
