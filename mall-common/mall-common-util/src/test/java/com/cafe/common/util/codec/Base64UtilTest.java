package com.cafe.common.util.codec;

import com.cafe.common.enumeration.media.MediaFormatEnum;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.codec
 * @Author: zhouboyi
 * @Date: 2023/7/28 15:43
 * @Description:
 */
class Base64UtilTest {

    private static final String PLAINTEXT = "123456";

    private static final String URL_PLAINTEXT = "https://github.com/zhouboyi1998";

    @Test
    void encode() {
        String ciphertext = Base64Util.encode(PLAINTEXT);
        System.out.println(ciphertext);
    }

    @Test
    void decode() {
        String ciphertext = Base64Util.encode(PLAINTEXT);
        String plaintext = Base64Util.decode(ciphertext);
        System.out.println(plaintext);
    }

    @Test
    void encodeUrl() {
        String ciphertext = Base64Util.encodeUrl(URL_PLAINTEXT);
        System.out.println(ciphertext);
    }

    @Test
    void decodeUrl() {
        String ciphertext = Base64Util.encodeUrl(URL_PLAINTEXT);
        String plaintext = Base64Util.decodeUrl(ciphertext);
        System.out.println(plaintext);
    }

    @Test
    void encodeImage() {
        BufferedImage bufferedImage = new BufferedImage(100, 100, 1);
        String base64Image = Base64Util.encodeImage(bufferedImage, MediaFormatEnum.PNG);
        System.out.println(base64Image);
    }
}
