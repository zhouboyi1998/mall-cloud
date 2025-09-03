package com.cafe.ordercenter.service;

import com.cafe.order.model.vo.OrderVO;
import com.cafe.storage.model.dto.CartDTO;

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
     * @param addressId   地址ID
     * @param cartDTOList 购物车DTO列表
     * @return 订单VO
     */
    OrderVO submit(Long addressId, List<CartDTO> cartDTOList);

    /**
     * 取消超时未支付的订单
     *
     * @param now      当前时间
     * @param duration 下单未支付时长 (单位: 分钟)
     */
    void cancel(LocalDateTime now, Integer duration);
}
