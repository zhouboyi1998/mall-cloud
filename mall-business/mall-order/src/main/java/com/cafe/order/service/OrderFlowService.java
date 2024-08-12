package com.cafe.order.service;

import com.cafe.order.model.OrderItem;
import com.cafe.order.vo.OrderVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.service
 * @Author: zhouboyi
 * @Date: 2023/10/27 14:58
 * @Description: 订单流转业务接口
 */
public interface OrderFlowService {

    /**
     * 保存订单
     *
     * @param orderVO
     * @return
     */
    OrderVO save(OrderVO orderVO);

    /**
     * 取消超时未支付的订单
     *
     * @param now      当前时间
     * @param duration 下单未支付时长 (单位: 分钟)
     * @return
     */
    List<OrderItem> cancel(LocalDateTime now, Integer duration);
}
