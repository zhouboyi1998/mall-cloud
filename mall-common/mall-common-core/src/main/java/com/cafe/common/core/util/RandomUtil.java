package com.cafe.common.core.util;

import java.util.ArrayList;
import java.util.Objects;
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
    private static ArrayList<String> createList(boolean hasNumber,
                                                boolean hasCapital,
                                                boolean hasLowercase) {
        String str = "";
        if (Objects.equals(true, hasNumber)) {
            str += number;
        }
        if (Objects.equals(true, hasCapital)) {
            str += capital;
        }
        if (Objects.equals(true, hasLowercase)) {
            str += lowercase;
        }
        char[] chars = str.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        for (char c : chars) {
            list.add(c + "");
        }
        return list;
    }

    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @return
     */
    public static String generate(int length) {
        ArrayList<String> list = createList(true, true, true);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int size = list.size();
            String randomStr = list.get(random.nextInt(size));
            sb.append(randomStr);
        }
        return sb.toString();
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
    public static String generate(int length,
                                  boolean hasNumber,
                                  boolean hasCapital,
                                  boolean hasLowercase) {
        ArrayList<String> list = createList(hasNumber, hasCapital, hasLowercase);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int size = list.size();
            String randomStr = list.get(random.nextInt(size));
            sb.append(randomStr);
        }
        return sb.toString();
    }

    /**
     * 生成 start 和 end 之间的一个整数
     *
     * @param start
     * @param end
     * @return
     */
    public static int generateNumber(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 生成随机手机号
     *
     * @return
     */
    public static String generateMobileNumber() {
        String mobileNumber = "";
        mobileNumber += mobileNumberBegin[generateNumber(0, mobileNumberBegin.length)];
        mobileNumber += generate(8, true, false, false);
        return mobileNumber;
    }
}
