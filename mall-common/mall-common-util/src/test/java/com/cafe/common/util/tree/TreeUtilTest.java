package com.cafe.common.util.tree;

import com.cafe.common.lang.datastructures.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.tree
 * @Author: zhouboyi
 * @Date: 2024/5/11 9:49
 * @Description:
 */
class TreeUtilTest {

    private static final List<TreeNode<Integer>> NODE_LIST = new ArrayList<>();

    static {
        NODE_LIST.add(new TreeNode<Integer>().setId(1).setParentId(0));
        NODE_LIST.add(new TreeNode<Integer>().setId(2).setParentId(0));
        NODE_LIST.add(new TreeNode<Integer>().setId(3).setParentId(0));

        NODE_LIST.add(new TreeNode<Integer>().setId(11).setParentId(1));
        NODE_LIST.add(new TreeNode<Integer>().setId(12).setParentId(1));
        NODE_LIST.add(new TreeNode<Integer>().setId(13).setParentId(1));

        NODE_LIST.add(new TreeNode<Integer>().setId(21).setParentId(2));
        NODE_LIST.add(new TreeNode<Integer>().setId(22).setParentId(2));

        NODE_LIST.add(new TreeNode<Integer>().setId(111).setParentId(11));
        NODE_LIST.add(new TreeNode<Integer>().setId(112).setParentId(11));
        NODE_LIST.add(new TreeNode<Integer>().setId(113).setParentId(11));

        NODE_LIST.add(new TreeNode<Integer>().setId(211).setParentId(21));
        NODE_LIST.add(new TreeNode<Integer>().setId(212).setParentId(21));
    }

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
