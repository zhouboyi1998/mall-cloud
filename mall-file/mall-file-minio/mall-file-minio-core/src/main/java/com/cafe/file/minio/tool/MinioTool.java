package com.cafe.file.minio.tool;

import cn.hutool.core.lang.UUID;
import org.springframework.stereotype.Component;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.tool
 * @Author: zhouboyi
 * @Date: 2022/6/9 11:31
 * @Description: Minio 工具类
 */
@Component
public class MinioTool {

    /**
     * 生成 UUID 文件名
     *
     * @return
     */
    public static String generateFileName() {
        // 生成 UUID 后去除其中的 - 符号
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 根据文件原始名称生成 UUID 文件名
     *
     * @param fileName
     * @return
     */
    public static String generateFileName(String fileName) {
        // 生成 UUID 后去除其中的 - 符号
        return UUID.fromString(fileName).toString().replace("-", "");
    }
}
