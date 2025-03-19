package com.cafe.foundation.model.dto;

import com.cafe.common.lang.tree.Tree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.model.vo
 * @Author: zhouboyi
 * @Date: 2024/5/11 10:21
 * @Description: 区域树数据传输模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "AreaTreeDTO", description = "区域树数据传输模型")
public class AreaTreeDTO extends Tree implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域ID")
    private Long id;

    @ApiModelProperty(value = "上级区域ID")
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
