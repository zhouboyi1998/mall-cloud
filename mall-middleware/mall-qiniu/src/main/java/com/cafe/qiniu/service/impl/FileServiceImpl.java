package com.cafe.qiniu.service.impl;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.id.feign.IDFeign;
import com.cafe.qiniu.service.FileService;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final Auth auth;

    private final BucketManager bucketManager;

    private final UploadManager uploadManager;

    private final IDFeign idFeign;

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
        log.info("FileServiceImpl.upload(): Upload file to Qiniu OSS! status code -> [{}], response body -> {}", response.statusCode, response.bodyString());
        return StringConstant.SLASH + bucket + StringConstant.SLASH + filename;
    }

    @SneakyThrows
    @Override
    public Integer delete(String bucket, String filename) {
        // 删除文件
        Response response = bucketManager.delete(bucket, filename);
        log.info("FileServiceImpl.delete(): Delete file from Qiniu OSS! status code -> [{}], response body -> {}", response.statusCode, response.bodyString());
        return response.statusCode;
    }
}
