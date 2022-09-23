package com.cafe.common.core.util;

import java.util.Random;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.util
 * @Author: zhouboyi
 * @Date: 2022/4/21 14:19
 * @Description: 生成随机内容工具类
 */
public class RandomUtil {

    private static Random random = new Random();

    /**
     * 数字
     */
    private static String number = "0123456789";

    /**
     * 大写字母
     */
    private static String capital = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 小写字母
     */
    private static String lowercase = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 手机号码开头
     */
    private static String[] mobileNumberBegin = {
        "130", "131", "132", "133", "134", "135", "136", "137", "138", "139",
        "145", "147", "149", "150", "151", "152", "153", "155", "156", "157",
        "158", "159", "166", "171", "175", "176", "177", "178", "180", "181",
        "185", "186", "187", "188", "189", "191", "198", "199"
    };

    /**
     * 构建字符池
     *
     * @param hasNumber    是否加入数字
     * @param hasCapital   是否加入大写字母
     * @param hasLowercase 是否加入小写字母
     * @return
     */
    private static String createCharacterPool(boolean hasNumber, boolean hasCapital, boolean hasLowercase) {
        StringBuilder builder = new StringBuilder();
        if (hasNumber) {
            builder.append(number);
        }
        if (hasCapital) {
            builder.append(capital);
        }
        if (hasLowercase) {
            builder.append(lowercase);
        }
        return builder.toString();
    }

    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @return
     */
    public static String generateString(int length) {
        String pool = createCharacterPool(true, true, true);
        int len = pool.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = pool.charAt(random.nextInt(len));
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * 生成随机字符串（可以选择字符池内容）
     *
     * @param length       字符串长度
     * @param hasNumber    是否加入数字
     * @param hasCapital   是否加入大写字母
     * @param hasLowercase 是否加入小写字母
     * @return
     */
    public static String generateString(int length, boolean hasNumber, boolean hasCapital, boolean hasLowercase) {
        String pool = createCharacterPool(hasNumber, hasCapital, hasLowercase);
        int len = pool.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = pool.charAt(random.nextInt(len));
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * 生成 start 和 end 之间的一个整数
     *
     * @param start
     * @param end
     * @return
     */
    public static int generateInteger(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 生成随机手机号
     *
     * @return
     */
    public static String generateMobileNumber() {
        StringBuilder builder = new StringBuilder();
        builder.append(mobileNumberBegin[generateInteger(0, mobileNumberBegin.length)]);
        builder.append(generateString(8, true, false, false));
        return builder.toString();
    }
}
