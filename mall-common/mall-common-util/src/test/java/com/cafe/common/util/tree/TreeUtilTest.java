package com.cafe.common.util.tree;

import com.cafe.common.lang.tree.Tree;
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

    private static final List<Tree> NODE_LIST = new ArrayList<>();

    static {
        NODE_LIST.add(new Tree().setId(1L).setParentId(0L));
        NODE_LIST.add(new Tree().setId(2L).setParentId(0L));
        NODE_LIST.add(new Tree().setId(3L).setParentId(0L));

        NODE_LIST.add(new Tree().setId(11L).setParentId(1L));
        NODE_LIST.add(new Tree().setId(12L).setParentId(1L));
        NODE_LIST.add(new Tree().setId(13L).setParentId(1L));

        NODE_LIST.add(new Tree().setId(21L).setParentId(2L));
        NODE_LIST.add(new Tree().setId(22L).setParentId(2L));

        NODE_LIST.add(new Tree().setId(111L).setParentId(11L));
        NODE_LIST.add(new Tree().setId(112L).setParentId(11L));
        NODE_LIST.add(new Tree().setId(113L).setParentId(11L));

        NODE_LIST.add(new Tree().setId(211L).setParentId(21L));
        NODE_LIST.add(new Tree().setId(212L).setParentId(21L));
    }

    @Test
    void buildTreeNode() {
        Tree tree = TreeUtil.buildTreeNode(NODE_LIST, 1L);
        System.out.println(tree);
    }

    @Test
    void buildTreeListWithoutParentId() {
        List<Tree> treeList = TreeUtil.buildTreeList(NODE_LIST);
        System.out.println(treeList);
    }

    @Test
    void buildTreeListWithParentId() {
        List<Tree> treeList = TreeUtil.buildTreeList(NODE_LIST, 2L);
        System.out.println(treeList);
    }
}
