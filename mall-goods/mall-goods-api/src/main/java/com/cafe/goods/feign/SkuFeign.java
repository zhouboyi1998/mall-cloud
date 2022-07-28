package com.cafe.goods.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.goods.dto.SkuElasticSearchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.feign
 * @Author: zhouboyi
 * @Date: 2022/7/27 17:44
 * @Description:
 */
@FeignClient(value = "mall-goods")
@RequestMapping(value = "/sku")
public interface SkuFeign {

    /**
     * 分页查询 SkuElasticSearchDTO 列表
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping(value = "/page/es/{current}/{size}")
    ResponseEntity<Page<SkuElasticSearchDTO>> pageSkuElasticSearchDTO(
        @PathVariable(value = "current") Long current,
        @PathVariable(value = "size") Long size
    );
}
