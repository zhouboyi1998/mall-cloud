package com.cafe.foundation.vo;

import com.cafe.common.lang.tree.Tree;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.vo
 * @Author: zhouboyi
 * @Date: 2024/5/11 10:21
 * @Description: 区域树视图模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MenuTreeVO", description = "区域树视图模型")
public class AreaTreeVO extends Tree implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "上级区域ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(value = "行政区划编码")
    private Integer areaCode;

    @ApiModelProperty(value = "邮政编码")
    private Integer postCode;

    @ApiModelProperty(value = "区域层级: 1 省份, 2 城市, 3 区县")
    private Integer level;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "状态: 0 禁用, 1 正常")
    private Integer status;
}
