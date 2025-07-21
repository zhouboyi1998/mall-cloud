package com.cafe.common.lang.datastructures.tree;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.datastructures.tree
 * @Author: zhouboyi
 * @Date: 2025/7/17 18:02
 * @Description: 字典树
 */
public interface Trie<T extends Trie<T, K>, K> {

    /**
     * 获取当前树节点的子节点集合
     *
     * @return 子节点集合
     */
    Map<K, T> getChildren();

    /**
     * 设置当前树节点的子节点集合
     *
     * @param children 子节点集合
     * @return 当前树节点
     */
    T setChildren(Map<K, T> children);

    /**
     * 获取当前树节点的失配指针
     *
     * @return 失配指针
     */
    TrieNode<K> getFailure();

    /**
     * 设置当前树节点的失配指针
     *
     * @param failure 失配指针
     * @return 当前树节点
     */
    T setFailure(TrieNode<K> failure);

    /**
     * 获取以当前节点为结束节点的关键词
     *
     * @return 关键词
     */
    K[] getKeyword();

    /**
     * 设置以当前节点为结束节点的关键词
     *
     * @param keyword 关键词
     * @return 当前树节点
     */
    T setKeyword(K[] keyword);
}
