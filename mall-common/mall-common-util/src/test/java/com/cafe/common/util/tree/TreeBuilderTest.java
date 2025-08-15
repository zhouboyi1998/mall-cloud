package com.cafe.common.util.tree;

import com.cafe.common.lang.datastructures.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static com.cafe.common.util.tree.Constant.NODE_LIST;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.tree
 * @Author: zhouboyi
 * @Date: 2025/8/15 22:16
 * @Description:
 */
public class TreeBuilderTest {

    @Test
    void buildTreeNode() {
        TreeNode<Integer> tree = TreeBuilder.<TreeNode<Integer>, Integer>builder()
            .nodeList(NODE_LIST)
            .idGetter(TreeNode::getId)
            .parentIdGetter(TreeNode::getParentId)
            .childrenSetter(TreeNode::setChildren)
            .rootNodeFilter(node -> Objects.equals(node.getId(), 1))
            .defaultRootSupplier(TreeNode::new)
            .build()
            .buildTreeNode();
        System.out.println(tree);
    }

    @Test
    void buildTreeList() {
        List<TreeNode<Integer>> treeList = TreeBuilder.<TreeNode<Integer>, Integer>builder()
            .nodeList(NODE_LIST)
            .idGetter(TreeNode::getId)
            .parentIdGetter(TreeNode::getParentId)
            .childrenSetter(TreeNode::setChildren)
            .firstLevelNodeFilter(node -> Objects.equals(node.getParentId(), 0))
            .parallelChecker(() -> NODE_LIST.size() > 100)
            .build()
            .buildTreeList();
        System.out.println(treeList);
    }
}
