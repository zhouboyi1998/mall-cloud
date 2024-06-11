package com.cafe.minio.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.minio.feign
 * @Author: zhouboyi
 * @Date: 2024/3/26 21:11
 * @Description:
 */
@FeignClient(value = "mall-minio", contextId = "bucket", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/bucket")
public interface BucketFeign {

    /**
     * 查询存储桶列表
     *
     * @return
     */
    @GetMapping(value = "/list")
    ResponseEntity<List<String>> list();

    /**
     * 查询存储桶是否存在
     *
     * @param bucket 存储桶
     * @return
     */
    @GetMapping(value = "/exists/{bucket}")
    ResponseEntity<Boolean> exists(@PathVariable(value = "bucket") String bucket);

    /**
     * 新建存储桶
     *
     * @param bucket 存储桶
     * @return
     */
    @PostMapping(value = "/create/{bucket}")
    ResponseEntity<Void> create(@PathVariable(value = "bucket") String bucket);

    /**
     * 删除存储桶
     *
     * @param bucket 存储桶
     * @return
     */
    @DeleteMapping(value = "/remove/{bucket}")
    ResponseEntity<Void> remove(@PathVariable(value = "bucket") String bucket);
}
