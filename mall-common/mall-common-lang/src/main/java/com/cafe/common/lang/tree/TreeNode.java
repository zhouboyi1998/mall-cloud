package com.cafe.common.lang.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.tree
 * @Author: zhouboyi
 * @Date: 2025/4/8 18:01
 * @Description: 默认树形节点
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TreeNode<K> implements Tree<TreeNode<K>, K> {

    private K id;

    private K parentId;

    private List<TreeNode<K>> children;
}
