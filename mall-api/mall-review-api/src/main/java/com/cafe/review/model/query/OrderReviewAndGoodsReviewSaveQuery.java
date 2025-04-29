package com.cafe.review.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.model.query
 * @Author: zhouboyi
 * @Date: 2025/4/29 18:01
 * @Description: 保存订单评论和商品评论请求条件
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OrderReviewAndGoodsReviewSaveQuery", description = "保存订单评论和商品评论请求条件")
public class OrderReviewAndGoodsReviewSaveQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "保存订单评论请求列表")
    private List<OrderReviewSaveQuery> orderReviewSaveQueryList;

    @ApiModelProperty(value = "保存商品评论请求列表")
    private List<GoodsReviewSaveQuery> goodsReviewSaveQueryList;
}
