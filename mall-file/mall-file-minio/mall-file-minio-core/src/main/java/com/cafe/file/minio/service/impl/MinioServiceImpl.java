package com.cafe.file.minio.service.impl;

import com.cafe.common.constant.StringConstant;
import com.cafe.file.minio.property.MinioProperties;
import com.cafe.file.minio.service.MinioService;
import com.cafe.id.feign.IDFeign;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.minio.service.impl
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:54
 * @Description:
 */
@Service
public class MinioServiceImpl implements MinioService {

    private final MinioProperties minioProperties;

    private final MinioClient minioClient;

    private final IDFeign idFeign;

    @Autowired
    public MinioServiceImpl(
        MinioProperties minioProperties,
        MinioClient minioClient,
        IDFeign idFeign
    ) {
        this.minioProperties = minioProperties;
        this.minioClient = minioClient;
        this.idFeign = idFeign;
    }

    @Override
    public String upload(String bucket, MultipartFile file) throws Exception {
        // 使用雪花算法生成文件名
        StringBuilder fileName = new StringBuilder(Objects.requireNonNull(idFeign.nextId().getBody()).toString());
        // 获取文件原名
        String originalFilename = file.getOriginalFilename();
        // 如果文件原名存在扩展名, 获取扩展名并拼接
        if (Objects.nonNull(originalFilename) && originalFilename.contains(StringConstant.POINT)) {
            fileName.append(originalFilename.substring(originalFilename.indexOf(StringConstant.POINT)));
        }

        // 构造文件上传参数
        PutObjectArgs args = PutObjectArgs
            .builder()
            .bucket(bucket)
            .object(fileName.toString())
            .contentType(file.getContentType())
            .stream(file.getInputStream(), file.getSize(), -1)
            .build();
        // 上传文件
        minioClient.putObject(args);
        // 获取文件存储路径
        return StringConstant.SLASH + bucket + StringConstant.SLASH + fileName;
    }

    @Override
    public void download(String bucket, String fileName, HttpServletResponse httpResponse) throws Exception {
        // 构造文件下载参数
        GetObjectArgs args = GetObjectArgs
            .builder()
            .bucket(bucket)
            .object(fileName)
            .build();
        GetObjectResponse minioResponse = minioClient.getObject(args);

        // 用于临时缓冲的 byte 数组
        byte[] buffer = new byte[1024];
        // 用于临时存储读取的字节长度
        int length;

        // 从 MinIO 服务器读取文件字节流
        FastByteArrayOutputStream byteArrayOS = new FastByteArrayOutputStream();
        while ((length = minioResponse.read(buffer)) != -1) {
            byteArrayOS.write(buffer, 0, length);
        }
        byteArrayOS.flush();

        // 转换成完整的文件字节流
        byte[] bytes = byteArrayOS.toByteArray();

        // 配置 HTTP Response
        httpResponse.setCharacterEncoding(minioProperties.getCharacterEncoding());
        httpResponse.setContentType(minioProperties.getContentType());
        httpResponse.setHeader(minioProperties.getHeaderKey(), minioProperties.getHeaderValuePrefix() + fileName);

        // 写入 HTTP Response 中
        ServletOutputStream httpOS = httpResponse.getOutputStream();
        httpOS.write(bytes);
        httpOS.flush();
    }

    @Override
    public String getFileUrl(String bucket, String fileName) throws Exception {
        // 构造获取文件外链参数
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs
            .builder()
            .bucket(bucket)
            .object(fileName)
            .method(Method.GET)
            .build();
        // 获取文件外链 URL
        return minioClient.getPresignedObjectUrl(args);
    }

    @Override
    public String getFileUrl(String bucket, String fileName, Integer expiry) throws Exception {
        // 构造获取文件外链参数
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs
            .builder()
            .bucket(bucket)
            .object(fileName)
            .method(Method.GET)
            .expiry(expiry)
            .build();
        // 获取文件外链 URL
        return minioClient.getPresignedObjectUrl(args);
    }
}
