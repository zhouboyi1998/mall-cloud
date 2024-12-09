package com.cafe.order.facade;

import com.cafe.order.model.entity.OrderItem;
import com.cafe.order.model.vo.OrderVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.facade
 * @Author: zhouboyi
 * @Date: 2023/10/27 14:58
 * @Description: 订单流转防腐层接口
 */
public interface OrderFlowFacade {

    /**
     * 保存订单
     *
     * @param orderVO 订单数据
     * @return 新保存的订单
     */
    OrderVO save(OrderVO orderVO);

    /**
     * 取消超时未支付的订单
     *
     * @param now      当前时间
     * @param duration 下单未支付时长 (单位: 分钟)
     * @return 被取消的订单明细
     */
    List<OrderItem> cancel(LocalDateTime now, Integer duration);
}
