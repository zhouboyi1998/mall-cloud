package com.cafe.order.converter;

import com.cafe.order.model.Order;
import com.cafe.order.vo.OrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.converter
 * @Author: zhouboyi
 * @Date: 2023/10/24 10:57
 * @Description:
 */
@Mapper(componentModel = "spring")
public interface OrderConverter {

    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    /**
     * 视图模型 -> 实体模型
     *
     * @param orderVO
     * @return
     */
    Order toModel(OrderVO orderVO);

    /**
     * 实体模型 -> 视图模型
     *
     * @param order
     * @return
     */
    OrderVO toVO(Order order);
}
