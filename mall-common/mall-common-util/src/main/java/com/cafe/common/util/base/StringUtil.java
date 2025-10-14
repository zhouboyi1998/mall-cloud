package com.cafe.common.util.base;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.base
 * @Author: zhouboyi
 * @Date: 2024/3/5 10:01
 * @Description: 字符串工具类
 */
public class StringUtil {

    /**
     * 字符串首字母转换为大写
     *
     * @param str 源字符串
     * @return 目标字符串
     */
    public static String upperCaseFirstLetter(String str) {
        return str == null || str.isEmpty() ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 字符串首字母转换为小写
     *
     * @param str 源字符串
     * @return 目标字符串
     */
    public static String lowerCaseFirstLetter(String str) {
        return str == null || str.isEmpty() ? str : str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
