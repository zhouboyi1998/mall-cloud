package com.cafe.security.management.tool;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.management.tool
 * @Author: zhouboyi
 * @Date: 2022/5/9 17:00
 * @Description: SpringSecurity 自定义密码工具箱
 */
public class PasswordToolbox {

    /**
     * BCrypt 加密
     *
     * @param plaintext 明文
     * @return 密文
     */
    public static String encodeBCrypt(String plaintext) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(plaintext);
    }

    /**
     * BCrypt 匹配
     *
     * @param ciphertext 密文
     * @param plaintext  明文
     * @return
     */
    public static Boolean matchBCrypt(String plaintext, String ciphertext) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plaintext, ciphertext);
    }

    /**
     * SCrypt 加密
     *
     * @param plaintext 明文
     * @return 密文
     */
    public static String encodeSCrypt(String plaintext) {
        PasswordEncoder passwordEncoder = new SCryptPasswordEncoder();
        return passwordEncoder.encode(plaintext);
    }

    /**
     * BCrypt 匹配
     *
     * @param ciphertext 密文
     * @param plaintext  明文
     * @return
     */
    public static Boolean matchSCrypt(String plaintext, String ciphertext) {
        PasswordEncoder passwordEncoder = new SCryptPasswordEncoder();
        return passwordEncoder.matches(plaintext, ciphertext);
    }

    public static void main(String[] args) {
        System.out.println(encodeSCrypt(""));
    }
}
