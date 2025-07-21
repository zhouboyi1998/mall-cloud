package com.cafe.common.lang.algorithm.automaton;

import com.cafe.common.lang.datastructures.tree.TrieNode;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.algorithm.automaton
 * @Author: zhouboyi
 * @Date: 2025/7/21 10:47
 * @Description:
 */
public class AhoCorasickTest {

    private static final String[] KEYWORDS = {"hello", "world", "abc", "xyz", "alpha"};

    private static final String TEXT = "hello world, let's begin to sing the alphabet song, abcdefg!";

    @Test
    void ahoCorasickAutomaton() {
        TrieNode<Character> root = AhoCorasick.buildTrie(KEYWORDS);
        List<String> matches = AhoCorasick.matchText(root, TEXT);
        matches.forEach(System.out::println);
    }
}
