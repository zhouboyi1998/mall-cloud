package com.cafe.elasticsearch.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.elasticsearch.index.GoodsIndex;
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
     * @param keyword
     * @return
     */
    @GetMapping(value = "/search/index")
    ResponseEntity<List<GoodsIndex>> searchIndex(@RequestParam(value = "keyword") String keyword);

    /**
     * 搜索商品ID
     *
     * @param keyword
     * @return
     */
    @GetMapping(value = "/search/id")
    ResponseEntity<List<Long>> searchId(@RequestParam(value = "keyword") String keyword);
}
