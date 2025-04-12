package com.cafe.goods.feign;

import com.cafe.goods.model.bo.Goods;
import com.cafe.starter.boot.interceptor.feign.FeignRequestInterceptor;
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
 * @Date: 2023/10/27 10:56
 * @Description:
 */
@FeignClient(value = "mall-goods", contextId = "goods", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/goods")
public interface GoodsFeign {

    /**
     * 根据库存量单位ids查询商品列表
     *
     * @param ids 库存量单位ID列表
     * @return 商品列表
     */
    @PostMapping(value = "/list")
    ResponseEntity<List<Goods>> list(@RequestBody List<Long> ids);
}
