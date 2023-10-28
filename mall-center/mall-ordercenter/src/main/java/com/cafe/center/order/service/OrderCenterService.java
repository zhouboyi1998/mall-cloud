package com.cafe.center.order.service;

import com.cafe.common.core.result.Result;
import com.cafe.merchant.vo.CartVO;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.center.order.service
 * @Author: zhouboyi
 * @Date: 2023/10/24 17:25
 * @Description: 订单中心业务接口
 */
public interface OrderCenterService {

    /**
     * 提交订单
     *
     * @param addressId
     * @param channel
     * @param invoice
     * @param cartVOList
     * @return
     */
    Result<Object> submit(Long addressId, Integer channel, Integer invoice, List<CartVO> cartVOList);
}
