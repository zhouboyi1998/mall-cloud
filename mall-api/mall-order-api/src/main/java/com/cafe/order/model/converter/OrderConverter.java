package com.cafe.order.model.converter;

import com.cafe.order.model.entity.Order;
import com.cafe.order.model.query.OrderQuery;
import com.cafe.order.model.vo.OrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.model.converter
 * @Author: zhouboyi
 * @Date: 2023/10/24 10:57
 * @Description: 订单模型转换器
 */
@Mapper(componentModel = "spring")
public interface OrderConverter {

    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    /**
     * 视图模型 -> 实体模型
     *
     * @param orderVO 视图模型
     * @return 实体模型
     */
    Order toEntity(OrderVO orderVO);

    /**
     * 查询模型 -> 实体模型
     *
     * @param orderQuery 查询模型
     * @return 实体模型
     */
    Order toEntity(OrderQuery orderQuery);

    /**
     * 实体模型 -> 视图模型
     *
     * @param order 实体模型
     * @return 视图模型
     */
    OrderVO toVO(Order order);

    /**
     * 实体模型列表 -> 视图模型列表
     *
     * @param orderList 实体模型列表
     * @return 视图模型列表
     */
    List<OrderVO> toVOList(List<Order> orderList);
}
