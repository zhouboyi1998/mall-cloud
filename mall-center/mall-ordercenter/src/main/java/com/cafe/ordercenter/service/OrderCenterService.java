package com.cafe.ordercenter.service;

import com.cafe.merchant.vo.CartVO;
import com.cafe.order.vo.OrderVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter.service
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
    OrderVO submit(Long addressId, Integer channel, Integer invoice, List<CartVO> cartVOList);

    /**
     * 自动取消超时未支付的订单
     *
     * @param now      当前时间
     * @param duration 下单未支付时长 (单位: 分钟)
     * @return
     */
    void autoCancel(LocalDateTime now, Integer duration);
}
