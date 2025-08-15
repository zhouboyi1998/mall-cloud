package com.cafe.common.util.tree;

import com.cafe.common.lang.datastructures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.tree
 * @Author: zhouboyi
 * @Date: 2025/8/15 22:20
 * @Description:
 */
public class Constant {

    protected static final List<TreeNode<Integer>> NODE_LIST = new ArrayList<>();

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
}
