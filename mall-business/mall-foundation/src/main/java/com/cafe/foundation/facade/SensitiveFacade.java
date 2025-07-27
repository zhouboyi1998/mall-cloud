package com.cafe.foundation.facade;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.facade
 * @Author: zhouboyi
 * @Date: 2025/7/27 21:34
 * @Description: 敏感词防腐层接口
 */
public interface SensitiveFacade {

    /**
     * 初始化敏感词字典树
     */
    void initSensitiveTrie();

    /**
     * 添加敏感词到字典树中
     *
     * @param sensitiveIds 敏感词ID列表
     */
    void addSensitiveWords(List<Long> sensitiveIds);

    /**
     * 匹配文本中的所有敏感词
     *
     * @param text 被校验的文本
     * @return 敏感词列表
     */
    List<String> matchText(String text);

    /**
     * 校验文本中是否包含敏感词
     *
     * @param text 被校验的文本
     * @return 是否包含敏感词
     */
    Boolean validateText(String text);
}
