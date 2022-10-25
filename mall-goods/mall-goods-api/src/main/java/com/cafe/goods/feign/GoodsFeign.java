package com.cafe.goods.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.goods.bo.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.feign
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:44
 * @Description:
 */
@FeignClient(value = "mall-goods")
@RequestMapping(value = "/goods")
public interface GoodsFeign {

    /**
     * 根据 SKU ids 查询商品列表
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "/list")
    ResponseEntity<List<Goods>> list(@RequestBody List<Long> ids);

    /**
     * 分页查询商品列表
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping(value = "/page/{current}/{size}")
    ResponseEntity<Page<Goods>> page(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    );
}
