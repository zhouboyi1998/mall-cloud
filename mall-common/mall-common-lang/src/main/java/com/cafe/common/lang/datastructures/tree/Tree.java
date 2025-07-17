package com.cafe.common.lang.datastructures.tree;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.datastructures.tree
 * @Author: zhouboyi
 * @Date: 2024/5/9 17:29
 * @Description: 普通树
 */
public interface Tree<T extends Tree<T, K>, K> {

    /**
     * 获取当前树节点的id
     *
     * @return 节点id
     */
    K getId();

    /**
     * 设置当前树节点的id
     *
     * @param id 节点id
     * @return 当前树节点
     */
    T setId(K id);

    /**
     * 获取当前树节点的父节点id
     *
     * @return 父节点id
     */
    K getParentId();

    /**
     * 设置当前树节点的父节点id
     *
     * @param parentId 父节点id
     * @return 当前树节点
     */
    T setParentId(K parentId);

    /**
     * 获取当前树节点的子节点列表
     *
     * @return 子节点列表
     */
    List<T> getChildren();

    /**
     * 设置当前树节点的子节点列表
     *
     * @param children 子节点列表
     * @return 当前树节点
     */
    T setChildren(List<T> children);
}
