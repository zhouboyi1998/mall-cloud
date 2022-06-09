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
    public static String generateFileName(String originalFilename, Boolean flag) {
        // 获取文件扩展名
        String extension = originalFilename.indexOf(".") > -1 ?
            originalFilename.substring(originalFilename.indexOf(".")) : "";
        // 生成 UUID, 去除其中的 - 符号, 添加文件扩展名
        if (flag) {
            // flag == true, 根据文件原始名生成 UUID
            return UUID.fromString(originalFilename).toString().replace("-", "") + extension;
        } else {
            // flag == false, 直接生成 UUID
            return UUID.randomUUID().toString().replace("-", "") + extension;
        }
    }
}
