package com.cafe.goods.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.goods.vo.SpuVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.feign
 * @Author: zhouboyi
 * @Date: 2024/8/4 23:30
 * @Description:
 */
@FeignClient(value = "mall-goods", contextId = "spu", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/spu")
public interface SpuFeign {

    /**
     * 根据 skuId 查询 SPU 视图模型
     *
     * @param skuId 库存量单位ID
     * @return 标准化产品单元VO
     */
    @GetMapping(value = "/vo/{skuId}")
    ResponseEntity<SpuVO> vo(@PathVariable(value = "skuId") Long skuId);
}
