package com.cafe.qiniu.service.impl;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.id.feign.IDFeign;
import com.cafe.qiniu.property.QiniuProperties;
import com.cafe.qiniu.service.QiniuService;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.qiniu.service.impl
 * @Author: zhouboyi
 * @Date: 2023/10/18 11:33
 * @Description:
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuServiceImpl.class);

    private final QiniuProperties qiniuProperties;

    private final IDFeign idFeign;

    private final Map<String, Region> regionMap;

    @Autowired
    public QiniuServiceImpl(
        QiniuProperties qiniuProperties,
        IDFeign idFeign,
        Map<String, Region> regionMap
    ) {
        this.qiniuProperties = qiniuProperties;
        this.idFeign = idFeign;
        this.regionMap = regionMap;
    }

    @Override
    public String upload(String region, String bucket, MultipartFile file) throws Exception {
        // 使用雪花算法生成文件名
        StringBuilder filename = new StringBuilder(Objects.requireNonNull(idFeign.nextId().getBody()).toString());
        // 获取文件原名
        String originalFilename = file.getOriginalFilename();
        // 如果文件原名存在扩展名, 获取扩展名并拼接
        if (Objects.nonNull(originalFilename) && originalFilename.contains(StringConstant.POINT)) {
            filename.append(originalFilename.substring(originalFilename.indexOf(StringConstant.POINT)));
        }

        // 生成令牌
        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        String uploadToken = auth.uploadToken(bucket);

        // 上传文件到七牛云
        Configuration configuration = new Configuration(regionMap.get(region));
        UploadManager uploadManager = new UploadManager(configuration);
        Response response = uploadManager.put(file.getBytes(), filename.toString(), uploadToken);

        LOGGER.info("QiniuServiceImpl.upload(): response -> {}", response);

        return StringConstant.SLASH + bucket + StringConstant.SLASH + filename;
    }
}
