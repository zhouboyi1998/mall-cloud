package com.cafe.review.model.query;

import com.cafe.review.model.entity.Review;
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
 * @Date: 2025/4/29 18:11
 * @Description: 保存商品评论请求条件
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GoodsReviewSaveQuery", description = "保存商品评论请求条件")
public class GoodsReviewSaveQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单明细ID")
    private Long orderItemId;

    @ApiModelProperty(value = "SPU ID")
    private Long spuId;

    @ApiModelProperty(value = "SKU ID")
    private Long skuId;

    @ApiModelProperty(value = "商品评论")
    private Review review;

    @ApiModelProperty(value = "标签ID列表")
    private List<Long> tagIds;
}
