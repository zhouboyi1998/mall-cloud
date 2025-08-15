package com.cafe.common.util.tree;

import com.cafe.common.lang.datastructures.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.cafe.common.util.tree.Constant.NODE_LIST;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.tree
 * @Author: zhouboyi
 * @Date: 2024/5/11 9:49
 * @Description:
 */
class TreeUtilTest {

    // -------------------- RecursiveBuilder --------------------

    @Test
    void buildTreeNodeWithRecursiveBuilder() {
        TreeNode<Integer> tree = TreeUtil.RecursiveBuilder.buildTreeNode(NODE_LIST, 1);
        System.out.println(tree);
    }

    @Test
    void buildTreeListByParentIdTypeWithRecursiveBuilder() {
        List<TreeNode<Integer>> treeList = TreeUtil.RecursiveBuilder.buildTreeList(NODE_LIST, Integer.class);
        System.out.println(treeList);
    }

    @Test
    void buildTreeListByParentIdWithRecursiveBuilder() {
        List<TreeNode<Integer>> treeList = TreeUtil.RecursiveBuilder.buildTreeList(NODE_LIST, 2);
        System.out.println(treeList);
    }

    // -------------------- RecursiveBuilder and Parallel --------------------

    @Test
    void buildTreeNodeWithRecursiveBuilderAndParallel() {
        TreeNode<Integer> tree = TreeUtil.RecursiveBuilder.buildTreeNode(NODE_LIST, 1, true);
        System.out.println(tree);
    }

    @Test
    void buildTreeListByParentIdTypeWithRecursiveBuilderAndParallel() {
        List<TreeNode<Integer>> treeList = TreeUtil.RecursiveBuilder.buildTreeList(NODE_LIST, Integer.class, true);
        System.out.println(treeList);
    }

    @Test
    void buildTreeListByParentIdWithRecursiveBuilderAndParallel() {
        List<TreeNode<Integer>> treeList = TreeUtil.RecursiveBuilder.buildTreeList(NODE_LIST, 2, true);
        System.out.println(treeList);
    }

    // -------------------- MapLookupBuilder --------------------

    @Test
    void buildTreeNodeWithMapLookupBuilder() {
        TreeNode<Integer> tree = TreeUtil.MapLookupBuilder.buildTreeNode(NODE_LIST, 1);
        System.out.println(tree);
    }

    @Test
    void buildTreeListByParentIdTypeWithMapLookupBuilder() {
        List<TreeNode<Integer>> treeList = TreeUtil.MapLookupBuilder.buildTreeList(NODE_LIST, Integer.class);
        System.out.println(treeList);
    }

    @Test
    void buildTreeListByParentIdWithMapLookupBuilder() {
        List<TreeNode<Integer>> treeList = TreeUtil.MapLookupBuilder.buildTreeList(NODE_LIST, 2);
        System.out.println(treeList);
    }

    // -------------------- MapLookupBuilder and Parallel --------------------

    @Test
    void buildTreeNodeWithMapLookupBuilderAndParallel() {
        TreeNode<Integer> tree = TreeUtil.MapLookupBuilder.buildTreeNode(NODE_LIST, 1, true);
        System.out.println(tree);
    }

    @Test
    void buildTreeListByParentIdTypeWithMapLookupBuilderAndParallel() {
        List<TreeNode<Integer>> treeList = TreeUtil.MapLookupBuilder.buildTreeList(NODE_LIST, Integer.class, true);
        System.out.println(treeList);
    }

    @Test
    void buildTreeListByParentIdWithMapLookupBuilderAndParallel() {
        List<TreeNode<Integer>> treeList = TreeUtil.MapLookupBuilder.buildTreeList(NODE_LIST, 2, true);
        System.out.println(treeList);
    }
}
