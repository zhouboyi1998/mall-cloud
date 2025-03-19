package com.cafe.common.util.codec;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.codec
 * @Author: zhouboyi
 * @Date: 2023/8/16 9:38
 * @Description: RSA 加密工具类
 */
public class RSAUtil {

    protected static final String ALGORITHM = "RSA";

    /**
     * 公钥加密
     *
     * @param plaintext 明文
     * @param publicKey 公钥
     * @return 密文 (转换成 Base64 编码返回)
     */
    @SneakyThrows
    public static String encrypt(String plaintext, RSAPublicKey publicKey) {
        // 获取 RSA 加解密器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为加密模式, 使用公钥加密
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 执行加密操作
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(ciphertext);
    }

    /**
     * 私钥解密
     *
     * @param ciphertext 密文（Base64 编码）
     * @param privateKey 私钥
     * @return 明文
     */
    @SneakyThrows
    public static String decrypt(String ciphertext, RSAPrivateKey privateKey) {
        // 获取 RSA 加解密器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为解密模式, 使用私钥解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 执行解密操作
        byte[] plaintext = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(plaintext, StandardCharsets.UTF_8);
    }

    /**
     * 私钥加密
     *
     * @param plaintext  明文
     * @param privateKey 私钥
     * @return 密文 (转换成 Base64 编码返回)
     */
    @SneakyThrows
    public static String encrypt(String plaintext, RSAPrivateKey privateKey) {
        // 获取 RSA 加解密器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为加密模式, 使用私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 执行加密操作
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(ciphertext);
    }

    /**
     * 公钥解密
     *
     * @param ciphertext 密文（Base64 编码）
     * @param publicKey  公钥
     * @return 明文
     */
    @SneakyThrows
    public static String decrypt(String ciphertext, RSAPublicKey publicKey) {
        // 获取 RSA 加解密器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为解密模式, 使用公钥解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        // 执行解密操作
        byte[] plaintext = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(plaintext, StandardCharsets.UTF_8);
    }
}
