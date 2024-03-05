package com.cafe.common.util;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util
 * @Author: zhouboyi
 * @Date: 2024/3/5 10:01
 * @Description: 字符串工具类
 */
public class StringUtil {

    public static String upperCaseFirstLetter(String str) {
        return str == null || str.length() == 0 ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String lowerCaseFirstLetter(String str) {
        return str == null || str.length() == 0 ? str : str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
