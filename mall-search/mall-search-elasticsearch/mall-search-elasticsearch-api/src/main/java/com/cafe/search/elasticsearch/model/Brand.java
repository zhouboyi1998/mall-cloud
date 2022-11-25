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
 * @Description: ElasticSearch 品牌实体模型
 */
@ApiModel(value = "Brand", description = "ElasticSearch 品牌实体模型")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌ID")
    private Long id;

    @ApiModelProperty(value = "品牌名称")
    @JsonSerialize(using = ToStringSerializer.class)
    private String brandName;

    public Long getId() {
        return id;
    }

    public Brand setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public Brand setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    @Override
    public String toString() {
        return "Brand{" +
            "id=" + id +
            ", brandName='" + brandName + '\'' +
            '}';
    }
}
