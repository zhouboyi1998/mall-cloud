package com.cafe.search.elasticsearch.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.elasticsearch.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 品牌
 */
@ApiModel(value = "Brand对象", description = "品牌")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌ID")
    private Long id;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "Brand{" +
            "id=" + id +
            ", brandName='" + brandName + '\'' +
            '}';
    }
}
