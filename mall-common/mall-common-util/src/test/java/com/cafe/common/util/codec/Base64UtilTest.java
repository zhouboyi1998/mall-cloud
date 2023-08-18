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

    @Test
    void encodeString() {
        String ciphertext = Base64Util.encode(PLAINTEXT);
        System.out.println(ciphertext);
    }

    @Test
    void decodeString() {
        String ciphertext = Base64Util.encode(PLAINTEXT);
        String plaintext = Base64Util.decode(ciphertext);
        System.out.println(plaintext);
    }

    @Test
    void encodeBufferedImage() {
        BufferedImage bufferedImage = new BufferedImage(100, 100, 1);
        String base64Image = Base64Util.encode(bufferedImage, MediaFormatEnum.PNG);
        System.out.println(base64Image);
    }
}