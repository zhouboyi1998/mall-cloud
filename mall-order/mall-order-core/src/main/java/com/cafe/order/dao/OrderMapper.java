package com.cafe.order.dao;

import com.cafe.order.model.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.dao
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单数据访问接口
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}