package com.cafe.goods.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.goods.model.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.feign
 * @Author: zhouboyi
 * @Date: 2023/10/25 10:03
 * @Description:
 */
@FeignClient(value = "mall-goods", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/sku")
public interface SkuFeign {

    /**
     * 根据ids查询未上架的库存量单位列表
     *
     * @param skuIds
     * @return
     */
    @PostMapping(value = "/unlisted")
    ResponseEntity<List<Sku>> unlisted(@RequestBody List<Long> skuIds);
}
