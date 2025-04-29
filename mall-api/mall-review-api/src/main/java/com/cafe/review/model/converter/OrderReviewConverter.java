package com.cafe.review.model.converter;

import com.cafe.review.model.entity.OrderReview;
import com.cafe.review.model.query.OrderReviewSaveQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.model.converter
 * @Author: zhouboyi
 * @Date: 2025/5/2 19:44
 * @Description: 订单-评论关联关系模型转换器
 */
@Mapper(componentModel = "spring")
public interface OrderReviewConverter {

    OrderReviewConverter INSTANCE = Mappers.getMapper(OrderReviewConverter.class);

    /**
     * 保存请求模型 -> 实体模型
     *
     * @param query 保存请求模型
     * @return 实体模型
     */
    OrderReview toEntity(OrderReviewSaveQuery query);
}
