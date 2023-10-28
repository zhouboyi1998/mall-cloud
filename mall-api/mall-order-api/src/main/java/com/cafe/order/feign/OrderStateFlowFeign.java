package com.cafe.order.feign;

import com.cafe.common.core.feign.FeignRequestInterceptor;
import com.cafe.order.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.feign
 * @Author: zhouboyi
 * @Date: 2023/10/27 15:07
 * @Description:
 */
@FeignClient(value = "mall-order", configuration = {FeignRequestInterceptor.class})
@RequestMapping(value = "/order-state-flow")
public interface OrderStateFlowFeign {

    /**
     * 保存订单
     *
     * @param orderVO
     * @return
     */
    @PostMapping(value = "/save")
    ResponseEntity<Void> save(@RequestBody OrderVO orderVO);
}
