package com.cafe.common.constant.oracle;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.constant.oracle
 * @Author: zhouboyi
 * @Date: 2024/2/4 1:19
 * @Description: Oracle 相关常量
 */
public class OracleConstant {

    /**
     * 正则表达式
     */
    public static class Pattern {

        /**
         * 正则表达式: NUMBER 数字类型
         */
        public static final String NUMBER_PATTERN = "(?i)NUMBER.*";

        /**
         * 正则表达式: NUMBER(0) 转换为 BigDecimal 类型
         */
        public static final String NUMBER_BIG_DECIMAL_PATTERN = "(?i)NUMBER\\(0\\)";

        /**
         * 正则表达式: NUMBER(1-4) 转换为 Boolean 类型
         */
        public static final String NUMBER_BOOLEAN_PATTERN = "(?i)NUMBER\\([1-4]\\)";

        /**
         * 正则表达式: NUMBER(5-11) 转换为 Integer 类型
         */
        public static final String NUMBER_INTEGER_PATTERN = "(?i)NUMBER\\(([5-9]|1[0-1])\\)";

        /**
         * 正则表达式: NUMBER(12-21) 转换为 Long 类型
         */
        public static final String NUMBER_LONG_PATTERN = "(?i)NUMBER\\((1[2-9]|2[0-1])\\)";
    }
}
