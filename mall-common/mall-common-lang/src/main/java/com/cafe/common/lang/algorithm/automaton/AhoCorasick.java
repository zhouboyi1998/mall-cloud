package com.cafe.common.lang.algorithm.automaton;

import com.cafe.common.lang.datastructures.tree.TrieNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.algorithm.automaton
 * @Author: zhouboyi
 * @Date: 2025/7/17 17:48
 * @Description: AC 自动机
 */
public class AhoCorasick {

    /**
     * 构建字典树
     *
     * @param keywords 关键词列表
     * @return 字典树根节点
     */
    public static TrieNode<Character> buildTrie(String... keywords) {
        TrieNode<Character> root = buildTrieNode();
        insertKeywords(root, keywords);
        buildFailurePointer(root);
        return root;
    }

    /**
     * 构建字典树节点
     *
     * @return 新建的字典树节点
     */
    public static TrieNode<Character> buildTrieNode() {
        return new TrieNode<Character>().setChildren(new HashMap<>());
    }

    /**
     * 插入关键词
     *
     * @param root     字典树根节点
     * @param keywords 关键词列表
     */
    public static void insertKeywords(TrieNode<Character> root, String... keywords) {
        // 遍历所有关键词
        for (String keyword : keywords) {
            // 每一个关键词都从根节点开始插入
            TrieNode<Character> node = root;
            // 遍历关键词的所有字符, 插入字符到字典树中
            for (char c : keyword.toCharArray()) {
                // 根据字符从子节点集合中获取对应的子节点, 如果该字符对应的子节点不存在则创建
                node = node.getChildren().computeIfAbsent(c, k -> buildTrieNode());
            }
            // 设置以当前节点为结束节点的关键词
            node.setKeyword(keyword.chars().mapToObj(c -> (char) c).toArray(Character[]::new));
        }
    }

    /**
     * 构建字典树中的失败指针
     *
     * @param root 字典树根节点
     */
    public static void buildFailurePointer(TrieNode<Character> root) {
        // 使用队列进行广度优先搜索 (BFS)
        Queue<TrieNode<Character>> queue = new LinkedList<>();

        // 遍历根节点的所有子节点
        for (TrieNode<Character> children : root.getChildren().values()) {
            // 根节点的子节点的失配指针均为根节点
            children.setFailure(root);
            queue.offer(children);
        }

        // 使用广度优先搜索遍历字典树剩余的节点
        while (!queue.isEmpty()) {
            TrieNode<Character> node = queue.poll();
            // 遍历当前节点的所有子节点
            for (Map.Entry<Character, TrieNode<Character>> entry : node.getChildren().entrySet()) {
                Character c = entry.getKey();
                TrieNode<Character> children = entry.getValue();

                // 获取当前子节点的失配指针的子节点, 如果为空, 继续通过失配指针向上寻找
                TrieNode<Character> failure = node.getFailure();
                while (failure != null && !failure.getChildren().containsKey(c)) {
                    failure = failure.getFailure();
                }

                // 设置当前子节点的失败指针
                children.setFailure(failure != null ? failure.getChildren().get(c) : root);

                queue.offer(children);
            }
        }
    }

    /**
     * 匹配文本
     *
     * @param root 字典树根节点
     * @param text 被校验的文本
     * @return 匹配命中的关键词列表
     */
    public static List<String> matchText(TrieNode<Character> root, String text) {
        List<String> matches = new ArrayList<>();
        TrieNode<Character> node = root;
        // 遍历文本的所有字符
        for (Character c : text.chars().mapToObj(c -> (char) c).toArray(Character[]::new)) {
            // 失配时, 沿着失配指针回退, 直到找到可以继续匹配的节点
            while (node != null && !node.getChildren().containsKey(c)) {
                node = node.getFailure();
            }
            if (node == null) {
                node = root;
                continue;
            }
            // 获取当前节点的子节点
            node = node.getChildren().get(c);
            // 判断当前节点是否为结束节点
            if (node.getKeyword() != null) {
                // 如果是, 添加匹配的关键词
                matches.add(Arrays.stream(node.getKeyword()).map(String::valueOf).collect(Collectors.joining("")));
            }
        }
        return matches;
    }
}
