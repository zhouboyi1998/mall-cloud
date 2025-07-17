package com.cafe.goods.model.vo;

import com.cafe.common.lang.datastructures.tree.Tree;
import com.cafe.goods.model.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.model.vo
 * @Author: zhouboyi
 * @Date: 2024/5/11 14:49
 * @Description: 分类树形视图模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CategoryTreeVO", description = "分类树形视图模型")
public class CategoryTreeVO extends Category implements Tree<CategoryTreeVO, Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    private Long id;

    @ApiModelProperty(value = "上级分类ID")
    private Long parentId;

    @ApiModelProperty(value = "子分类列表")
    private List<CategoryTreeVO> children;
}
