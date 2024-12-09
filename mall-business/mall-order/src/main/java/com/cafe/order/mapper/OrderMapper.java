package com.cafe.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.order.model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单数据访问接口
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
