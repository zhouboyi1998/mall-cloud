package com.cafe.foundation.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.model.query
 * @Author: zhouboyi
 * @Date: 2024/12/14 19:36
 * @Description: 区域树列表查询条件
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AreaTreeListQuery", description = "区域树列表查询条件")
public class AreaTreeListQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域ID")
    private Long id;

    @ApiModelProperty(value = "上级区域ID")
    @NotNull(message = "上级区域ID不能为空")
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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
