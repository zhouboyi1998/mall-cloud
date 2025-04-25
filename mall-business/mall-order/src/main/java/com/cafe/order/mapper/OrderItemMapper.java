package com.cafe.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.order.model.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细数据访问接口
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
