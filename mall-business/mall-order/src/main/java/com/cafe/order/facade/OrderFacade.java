package com.cafe.order.facade;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cafe.order.model.entity.Order;
import com.cafe.order.model.query.OrderQuery;
import com.cafe.order.model.vo.OrderVO;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.facade
 * @Author: zhouboyi
 * @Date: 2024/12/6 19:26
 * @Description: 订单防腐层接口
 */
public interface OrderFacade {

    /**
     * 根据查询条件查询订单列表 (附带订单明细)
     *
     * @param page       分页参数
     * @param orderQuery 订单参数
     * @return 订单分页结果
     */
    Page<OrderVO> query(Page<Order> page, OrderQuery orderQuery);
}
