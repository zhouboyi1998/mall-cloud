package com.cafe.minio.service.impl;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.id.feign.IDFeign;
import com.cafe.minio.property.MinioProperties;
import com.cafe.minio.service.FileService;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.service.impl
 * @Author: zhouboyi
 * @Date: 2022/6/9 10:54
 * @Description:
 */
@Service
public class FileServiceImpl implements FileService {

    private final MinioProperties minioProperties;

    private final MinioClient minioClient;

    private final IDFeign idFeign;

    @Autowired
    public FileServiceImpl(MinioProperties minioProperties, MinioClient minioClient, IDFeign idFeign) {
        this.minioProperties = minioProperties;
        this.minioClient = minioClient;
        this.idFeign = idFeign;
    }

    @SneakyThrows
    @Override
    public String upload(String bucket, MultipartFile file) {
        // 使用雪花算法生成文件名
        StringBuilder filename = new StringBuilder(Objects.requireNonNull(idFeign.nextId().getBody()).toString());
        // 获取文件原名
        String originalFilename = file.getOriginalFilename();
        // 如果文件原名存在扩展名, 获取扩展名并拼接
        if (Objects.nonNull(originalFilename) && originalFilename.contains(StringConstant.POINT)) {
            filename.append(originalFilename.substring(originalFilename.indexOf(StringConstant.POINT)));
        }

        // 构造参数
        PutObjectArgs args = PutObjectArgs.builder()
            .bucket(bucket)
            .object(filename.toString())
            .contentType(file.getContentType())
            .stream(file.getInputStream(), file.getSize(), -1)
            .build();
        // 上传文件
        minioClient.putObject(args);
        // 获取文件存储路径
        return StringConstant.SLASH + bucket + StringConstant.SLASH + filename;
    }

    @SneakyThrows
    @Override
    public void download(String bucket, String filename, HttpServletResponse httpResponse) {
        // 构造参数
        GetObjectArgs args = GetObjectArgs.builder()
            .bucket(bucket)
            .object(filename)
            .build();
        // 获取文件
        GetObjectResponse minioResponse = minioClient.getObject(args);

        // 用于临时缓冲的字节数组
        byte[] buffer = new byte[1024];
        // 用于临时存储读取的字节长度
        int length;

        // 读取文件到临时缓冲的字节数组中, 并写入到字节输出流
        FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream();
        while ((length = minioResponse.read(buffer)) != -1) {
            fastByteArrayOutputStream.write(buffer, 0, length);
        }
        fastByteArrayOutputStream.flush();

        // 输出完整的文件字节数据到一个字节数组中
        byte[] bytes = fastByteArrayOutputStream.toByteArray();

        // 配置 HTTP Response
        httpResponse.setCharacterEncoding(minioProperties.getCharacterEncoding());
        httpResponse.setContentType(minioProperties.getContentType());
        httpResponse.setHeader(minioProperties.getHeaderKey(), minioProperties.getHeaderValuePrefix() + filename);

        // 将要下载的文件写入 HTTP Response 中
        ServletOutputStream servletOutputStream = httpResponse.getOutputStream();
        servletOutputStream.write(bytes);
        servletOutputStream.flush();
    }

    @SneakyThrows
    @Override
    public String url(String bucket, String filename, Integer expiry) {
        // 构造参数
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
            .bucket(bucket)
            .object(filename)
            .method(Method.GET)
            .expiry(expiry)
            .build();
        // 获取文件外链
        return minioClient.getPresignedObjectUrl(args);
    }
}
