package com.cafe.order.service;

import com.cafe.order.model.OrderDetail;
import com.cafe.order.vo.OrderVO;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 自动取消超时未支付的订单
     *
     * @param now      当前时间
     * @param duration 下单未支付时长 (单位: 分钟)
     * @return
     */
    List<OrderDetail> autoCancel(LocalDateTime now, Integer duration);
}
