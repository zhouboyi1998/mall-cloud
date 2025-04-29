package com.cafe.ordercenter.facade;

import com.cafe.order.model.vo.OrderVO;
import com.cafe.review.model.query.OrderReviewAndGoodsReviewSaveQuery;
import com.cafe.storage.model.dto.CartDTO;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter.facade
 * @Author: zhouboyi
 * @Date: 2025/3/24 23:05
 * @Description: 订单中心防腐层接口
 */
public interface OrderCenterFacade {

    /**
     * 提交订单
     *
     * @param addressId   地址ID
     * @param cartDTOList 购物车DTO列表
     * @return 订单VO
     */
    OrderVO submit(Long addressId, List<CartDTO> cartDTOList);

    /**
     * 评价订单
     *
     * @param query 保存订单评论和商品评论请求条件
     */
    void review(OrderReviewAndGoodsReviewSaveQuery query);
}
