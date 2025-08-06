package com.cafe.foundation.facade.impl;

import com.cafe.common.constant.model.SensitiveConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.lang.algorithm.automaton.AhoCorasick;
import com.cafe.common.lang.datastructures.tree.TrieNode;
import com.cafe.foundation.facade.SensitiveFacade;
import com.cafe.foundation.model.entity.Interference;
import com.cafe.foundation.model.entity.Sensitive;
import com.cafe.foundation.service.InterferenceService;
import com.cafe.foundation.service.SensitiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.facade.impl
 * @Author: zhouboyi
 * @Date: 2025/7/27 21:35
 * @Description: 敏感词防腐层实现类
 */
@RequiredArgsConstructor
@Service
public class SensitiveFacadeImpl implements SensitiveFacade {

    /**
     * 读写锁
     */
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    /**
     * 敏感词字典树根节点
     */
    private static TrieNode<Character> SENSITIVE_TRIE_ROOT;

    private final SensitiveService sensitiveService;

    private final InterferenceService interferenceService;

    @PostConstruct
    @Override
    public void initSensitiveTrie() {
        // 获取写锁
        READ_WRITE_LOCK.writeLock().lock();
        try {
            // 查询所有启用的敏感词
            String[] sensitiveWordArray = sensitiveService.lambdaQuery()
                .eq(Sensitive::getStatus, SensitiveConstant.Status.ENABLE)
                .list()
                .stream()
                .map(Sensitive::getSensitiveWord)
                // 忽略大小写
                .map(String::toLowerCase)
                .toArray(String[]::new);
            // 构建敏感词字典树
            SENSITIVE_TRIE_ROOT = AhoCorasick.buildTrie(sensitiveWordArray);
        } finally {
            // 释放写锁
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    @Override
    public void addSensitiveWords(List<Long> sensitiveIds) {
        // 获取写锁
        READ_WRITE_LOCK.writeLock().lock();
        try {
            // 查询敏感词
            String[] sensitiveWordArray = sensitiveService.lambdaQuery()
                .in(Sensitive::getId, sensitiveIds)
                .eq(Sensitive::getStatus, SensitiveConstant.Status.ENABLE)
                .list()
                .stream()
                .map(Sensitive::getSensitiveWord)
                // 忽略大小写
                .map(String::toLowerCase)
                .toArray(String[]::new);
            // 添加敏感词到字典树中
            AhoCorasick.insertKeywords(SENSITIVE_TRIE_ROOT, sensitiveWordArray);
        } finally {
            // 释放写锁
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    @Override
    public List<String> matchText(String text) {
        // 获取读锁
        READ_WRITE_LOCK.readLock().lock();
        try {
            // 忽略大小写
            text = text.toLowerCase();
            // 移除干扰符
            List<Interference> interferenceList = interferenceService.enableList();
            for (Interference interference : interferenceList) {
                text = text.replaceAll(interference.getInterferenceSymbol(), StringConstant.EMPTY);
            }
            // 匹配文本中的所有敏感词
            return AhoCorasick.matchText(SENSITIVE_TRIE_ROOT, text);
        } finally {
            // 释放读锁
            READ_WRITE_LOCK.readLock().unlock();
        }
    }

    @Override
    public Boolean validateText(String text) {
        return !CollectionUtils.isEmpty(matchText(text));
    }
}
