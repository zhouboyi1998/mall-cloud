package com.cafe.merchant.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.merchant.vo.CartVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.feign
 * @Author: zhouboyi
 * @Date: 2023/10/25 17:21
 * @Description:
 */
@FeignClient(value = "mall-merchant", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/stock")
public interface StockFeign {

    /**
     * 批量出库
     *
     * @param cartVOList
     * @return 库存不足的 SKU 主键列表
     */
    @PutMapping(value = "/outbound/batch")
    ResponseEntity<List<String>> outboundBatch(@RequestBody List<CartVO> cartVOList);
}
