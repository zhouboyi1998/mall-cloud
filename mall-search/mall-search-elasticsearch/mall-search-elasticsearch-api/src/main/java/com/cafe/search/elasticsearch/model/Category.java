package com.cafe.search.elasticsearch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类 (实体类)
 */
@ApiModel(value = "Category对象", description = "分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

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

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", categoryName='" + categoryName + '\'' +
            '}';
    }
}
