package com.cafe.order.service;

import com.cafe.order.vo.OrderVO;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service
 * @Author: zhouboyi
 * @Date: 2023/10/27 14:58
 * @Description: 订单状态流转业务接口
 */
public interface OrderStateFlowService {

    /**
     * 保存订单
     *
     * @param orderVO
     * @return
     */
    void save(OrderVO orderVO);
}
