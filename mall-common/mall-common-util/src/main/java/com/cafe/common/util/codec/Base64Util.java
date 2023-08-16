package com.cafe.common.util.codec;

import com.cafe.common.enumeration.media.MediaFormatEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Base64Util.class);

    /**
     * 字符串 Base64 编码
     *
     * @param plaintext 字符串明文
     * @return
     */
    public static String encode(String plaintext) {
        return Base64.getEncoder().encodeToString(plaintext.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 字符串 Base64 解码
     *
     * @param ciphertext 字符串密文
     * @return
     */
    public static String decode(String ciphertext) {
        return new String(Base64.getDecoder().decode(ciphertext), StandardCharsets.UTF_8);
    }

    /**
     * 图片缓冲 Base64 编码
     *
     * @param bufferedImage 图片缓冲
     * @return
     */
    public static String encode(BufferedImage bufferedImage, MediaFormatEnum mediaFormat) {
        // 将图片转换成 Base64 编码
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, mediaFormat.getFormat(), outputStream);
        } catch (IOException e) {
            LOGGER.error("Base64Util.encode(): Could not convert image to base64! message -> {}", e.getMessage(), e);
        }
        return mediaFormat.getBase64Prefix() + Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}
