package com.cafe.foundation.model.vo;

import com.cafe.common.lang.tree.Tree;
import com.cafe.foundation.model.entity.Area;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.model.vo
 * @Author: zhouboyi
 * @Date: 2024/5/11 10:21
 * @Description: 区域树形视图模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "AreaTreeVO", description = "区域树形视图模型")
public class AreaTreeVO extends Area implements Tree<AreaTreeVO, Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域ID")
    private Long id;

    @ApiModelProperty(value = "上级区域ID")
    private Long parentId;

    @ApiModelProperty(value = "子区域列表")
    private List<AreaTreeVO> children;
}
