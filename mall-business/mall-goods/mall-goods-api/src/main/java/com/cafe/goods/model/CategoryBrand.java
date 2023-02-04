package com.cafe.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description: 分类-品牌关联关系实体模型
 */
@ApiModel(value = "CategoryBrand", description = "分类-品牌关联关系实体模型")
@TableName("mall_category_brand")
public class CategoryBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类-品牌关联关系ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    @ApiModelProperty(value = "品牌ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long brandId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public CategoryBrand setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public CategoryBrand setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Long getBrandId() {
        return brandId;
    }

    public CategoryBrand setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public CategoryBrand setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public CategoryBrand setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryBrand{" +
            "id=" + id +
            ", categoryId=" + categoryId +
            ", brandId=" + brandId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
