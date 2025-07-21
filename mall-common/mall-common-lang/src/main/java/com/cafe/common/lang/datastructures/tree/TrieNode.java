package com.cafe.common.lang.datastructures.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.datastructures.tree
 * @Author: zhouboyi
 * @Date: 2025/7/17 18:06
 * @Description: 字典树节点
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TrieNode<K> implements Trie<TrieNode<K>, K>, Serializable {

    private static final long serialVersionUID = 1L;

    private Map<K, TrieNode<K>> children;

    private TrieNode<K> failure;

    private K[] keyword;
}
