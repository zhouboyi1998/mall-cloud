package com.cafe.security.util;

import org.junit.jupiter.api.Test;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.util
 * @Author: zhouboyi
 * @Date: 2023/7/28 16:29
 * @Description:
 */
class PasswordUtilTest {

    private static final String PLAINTEXT = "123456";

    @Test
    void encodeBCrypt() {
        String ciphertext = PasswordUtil.encodeBCrypt(PLAINTEXT);
        System.out.println(ciphertext);
    }

    @Test
    void matchBCrypt() {
        String ciphertext = PasswordUtil.encodeBCrypt(PLAINTEXT);
        Boolean match = PasswordUtil.matchBCrypt(PLAINTEXT, ciphertext);
        System.out.println(match);
    }

    @Test
    void encodeSCrypt() {
        String ciphertext = PasswordUtil.encodeSCrypt(PLAINTEXT);
        System.out.println(ciphertext);
    }

    @Test
    void matchSCrypt() {
        String ciphertext = PasswordUtil.encodeSCrypt(PLAINTEXT);
        Boolean match = PasswordUtil.matchSCrypt(PLAINTEXT, ciphertext);
        System.out.println(match);
    }
}
