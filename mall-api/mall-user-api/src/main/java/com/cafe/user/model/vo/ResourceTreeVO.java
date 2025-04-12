package com.cafe.user.model.vo;

import com.cafe.common.lang.tree.Tree;
import com.cafe.user.model.entity.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.model.vo
 * @Author: zhouboyi
 * @Date: 2022/7/7 17:25
 * @Description: 资源树形视图模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ResourceTreeVO", description = "资源树形视图模型")
public class ResourceTreeVO extends Resource implements Tree<ResourceTreeVO, Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源ID")
    private Long id;

    @ApiModelProperty(value = "上级资源ID")
    private Long parentId;

    @ApiModelProperty(value = "子资源列表")
    private List<ResourceTreeVO> children;
}
