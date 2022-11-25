package com.cafe.search.elasticsearch.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: ElasticSearch 分类实体模型
 */
@ApiModel(value = "Category", description = "ElasticSearch 分类实体模型")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Category setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", categoryName='" + categoryName + '\'' +
            '}';
    }
}
