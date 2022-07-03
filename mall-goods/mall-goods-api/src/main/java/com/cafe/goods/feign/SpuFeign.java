package com.cafe.goods.feign;

import com.cafe.goods.model.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.feign
 * @Author: zhouboyi
 * @Date: 2022/5/6 9:25
 * @Description:
 */
@FeignClient(value = "mall-goods")
@RequestMapping(value = "/spu")
public interface SpuFeign {

    /**
     * 根据id查询单个Standard Product Unit 标准化产品单元
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/one/{id}")
    ResponseEntity<Spu> one(@PathVariable(value = "id") Long id);
}
