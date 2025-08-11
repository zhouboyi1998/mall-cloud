package com.cafe.common.constant.model;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.model
 * @Author: zhouboyi
 * @Date: 2025/7/27 21:58
 * @Description: 敏感词相关常量
 */
public class SensitiveConstant {

    /**
     * 状态
     */
    public static class Status {

        /**
         * 禁用
         */
        public static final Integer DISABLE = 0;

        /**
         * 启用
         */
        public static final Integer ENABLE = 1;
    }

    /**
     * 消息类型
     */
    public static class MessageType {

        /**
         * 初始化敏感词字典树
         */
        public static final String REINITIALIZE_SENSITIVE_TRIE = "reinitialize-sensitive-trie";

        /**
         * 添加敏感词到字典树中
         */
        public static final String ADD_SENSITIVE_WORDS = "add-sensitive-words";
    }
}
