package com.cafe.common.util.codec;

import com.cafe.common.enumeration.media.MediaFormatEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
     * BufferedImage 格式图片转换成 Base64 编码
     *
     * @param bufferedImage
     * @return
     */
    public static String encode(BufferedImage bufferedImage, MediaFormatEnum mediaFormat) {
        // 将图片转换成 Base64 编码
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, mediaFormat.getFormat(), outputStream);
        } catch (IOException e) {
            LOGGER.error("Base64Util.encode(): could not convert image to base64 -> {}", e.getMessage());
        }
        return mediaFormat.getBase64Prefix() + Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}
