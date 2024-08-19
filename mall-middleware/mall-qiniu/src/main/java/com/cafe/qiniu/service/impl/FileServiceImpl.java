package com.cafe.qiniu.service.impl;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.id.feign.IDFeign;
import com.cafe.qiniu.service.FileService;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.service.impl
 * @Author: zhouboyi
 * @Date: 2023/10/18 11:33
 * @Description:
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    private final Auth auth;

    private final BucketManager bucketManager;

    private final UploadManager uploadManager;

    private final IDFeign idFeign;

    @Autowired
    public FileServiceImpl(Auth auth, BucketManager bucketManager, UploadManager uploadManager, IDFeign idFeign) {
        this.auth = auth;
        this.bucketManager = bucketManager;
        this.uploadManager = uploadManager;
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

        // 生成文件上传令牌
        String uploadToken = auth.uploadToken(bucket);
        // 上传文件
        Response response = uploadManager.put(file.getBytes(), filename.toString(), uploadToken);
        LOGGER.info("FileServiceImpl.upload(): response -> {}", response);

        return StringConstant.SLASH + bucket + StringConstant.SLASH + filename;
    }

    @SneakyThrows
    @Override
    public void delete(String bucket, String filename) {
        // 删除文件
        Response response = bucketManager.delete(bucket, filename);
        LOGGER.info("FileServiceImpl.delete(): response -> {}", response);
    }
}
