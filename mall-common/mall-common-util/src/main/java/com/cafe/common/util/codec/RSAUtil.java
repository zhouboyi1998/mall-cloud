package com.cafe.common.util.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);

    protected static final String ALGORITHM = "RSA";

    /**
     * 公钥加密
     *
     * @param plaintext 明文
     * @param publicKey 公钥
     * @return 密文 (转换成 Base64 编码返回)
     */
    public static String encrypt(String plaintext, RSAPublicKey publicKey) {
        byte[] ciphertext = new byte[]{};
        try {
            // 获取 RSA 加解密器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化为加密模式, 使用公钥加密
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 执行加密操作
            ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            LOGGER.error("RSAUtil.encrypt(): Could not get Cipher instance! algorithm -> {}, message -> {}", ALGORITHM, e.getMessage(), e);
        } catch (InvalidKeyException e) {
            LOGGER.error("RSAUtil.encrypt(): Could not init Cipher encrypt mode! public key -> {}, message -> {}", publicKey, e.getMessage(), e);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error("RSAUtil.encrypt(): Could not encrypt! plaintext -> {}, message -> {}", plaintext, e.getMessage(), e);
        }
        return Base64.getEncoder().encodeToString(ciphertext);
    }

    /**
     * 私钥解密
     *
     * @param ciphertext 密文（Base64 编码）
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decrypt(String ciphertext, RSAPrivateKey privateKey) {
        byte[] plaintext = new byte[]{};
        try {
            // 获取 RSA 加解密器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化为解密模式, 使用私钥解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // 执行解密操作
            plaintext = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            LOGGER.error("RSAUtil.decrypt(): Could not get Cipher instance! algorithm -> {}, message -> {}", ALGORITHM, e.getMessage(), e);
        } catch (InvalidKeyException e) {
            LOGGER.error("RSAUtil.decrypt(): Could not init Cipher decrypt mode! private key -> {}, message -> {}", privateKey, e.getMessage(), e);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error("RSAUtil.decrypt(): Could not decrypt! ciphertext -> {}, message -> {}", ciphertext, e.getMessage(), e);
        }
        return new String(plaintext, StandardCharsets.UTF_8);
    }
}
