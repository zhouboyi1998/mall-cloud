package com.cafe.elasticsearch.feign;

import com.cafe.common.constant.database.DatabaseConstant;
import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.cafe.elasticsearch.model.index.GoodsIndex;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param current   页码
     * @param size      每页数据数量
     * @param sortField 排序字段
     * @param sortRule  排序规则
     * @param keyword   关键词
     * @return 商品索引列表
     */
    @GetMapping(value = "/search/{current}/{size}")
    ResponseEntity<Page<GoodsIndex>> search(
        @PathVariable(value = "current") Integer current,
        @PathVariable(value = "size") Integer size,
        @RequestParam(value = "sortField", required = false, defaultValue = ElasticSearchConstant.Goods.DEFAULT_SORT_FIELD) String sortField,
        @RequestParam(value = "sortRule", required = false, defaultValue = DatabaseConstant.Rule.DESC) String sortRule,
        @RequestParam(value = "keyword", required = false) String keyword
    );
}
