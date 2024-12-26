package com.cafe.common.util.codec;

import com.cafe.common.enumeration.media.MediaFormatEnum;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.codec
 * @Author: zhouboyi
 * @Date: 2023/2/25 0:20
 * @Description: Base64 编码工具类
 */
public class Base64Util {

    /**
     * 字符串 Base64 编码
     *
     * @param plaintext 字符串明文
     * @return 字符串密文
     */
    public static String encode(String plaintext) {
        return Base64.getEncoder().encodeToString(plaintext.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 字符串 Base64 解码
     *
     * @param ciphertext 字符串密文
     * @return 字符串明文
     */
    public static String decode(String ciphertext) {
        return new String(Base64.getDecoder().decode(ciphertext), StandardCharsets.UTF_8);
    }

    /**
     * URL Base64 编码
     *
     * @param plaintext URL 明文
     * @return URL 密文
     */
    public static String encodeUrl(String plaintext) {
        return Base64.getUrlEncoder().encodeToString(plaintext.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * URL Base64 解码
     *
     * @param ciphertext URL 密文
     * @return URL 明文
     */
    public static String decodeUrl(String ciphertext) {
        return new String(Base64.getUrlDecoder().decode(ciphertext), StandardCharsets.UTF_8);
    }

    /**
     * 图片缓冲 Base64 编码
     *
     * @param bufferedImage 图片缓冲
     * @return 图片 Base64 编码
     */
    @SneakyThrows
    public static String encodeImage(BufferedImage bufferedImage, MediaFormatEnum mediaFormat) {
        // 将图片转换成 Base64 编码
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, mediaFormat.getFormat(), outputStream);
        return mediaFormat.getBase64Prefix() + Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}
