package com.cafe.common.util.codec;

import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.codec
 * @Author: zhouboyi
 * @Date: 2023/8/16 11:36
 * @Description:
 */
class RSAUtilTest {

    private static final String PLAINTEXT = "123456";

    private static RSAPublicKey PUBLIC_KEY;

    private static RSAPrivateKey PRIVATE_KEY;

    static {
        try {
            // 获取 RSA 密钥对生成器
            KeyPairGenerator generator = KeyPairGenerator.getInstance(RSAUtil.ALGORITHM);
            // 设置密钥长度
            generator.initialize(1024);
            // 生成 RSA 密钥对
            KeyPair keyPair = generator.generateKeyPair();
            // 获取 RSA 公钥
            PUBLIC_KEY = (RSAPublicKey) keyPair.getPublic();
            // 获取 RSA 私钥
            PRIVATE_KEY = (RSAPrivateKey) keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    void encrypt() {
        String ciphertext = RSAUtil.encrypt(PLAINTEXT, PUBLIC_KEY);
        System.out.println(ciphertext);
    }

    @Test
    void decrypt() {
        String ciphertext = RSAUtil.encrypt(PLAINTEXT, PUBLIC_KEY);
        String plaintext = RSAUtil.decrypt(ciphertext, PRIVATE_KEY);
        System.out.println(plaintext);
    }
}
