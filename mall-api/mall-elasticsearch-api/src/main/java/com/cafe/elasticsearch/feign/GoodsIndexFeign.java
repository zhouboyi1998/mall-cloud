package com.cafe.elasticsearch.feign;

import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.feign
 * @Author: zhouboyi
 * @Date: 2024/8/1 0:14
 * @Description:
 */
@FeignClient(value = "mall-elasticsearch", contextId = "goods-index", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/goods-index")
public interface GoodsIndexFeign {

    /**
     * 搜索商品索引
     *
     * @param keyword 关键词
     * @return 商品索引列表
     */
    @GetMapping(value = "/search/index")
    ResponseEntity<List<GoodsIndex>> searchIndex(@RequestParam(value = "keyword") String keyword);

    /**
     * 搜索商品ID
     *
     * @param keyword 关键词
     * @return 商品ID列表
     */
    @GetMapping(value = "/search/id")
    ResponseEntity<List<Long>> searchId(@RequestParam(value = "keyword") String keyword);
}
