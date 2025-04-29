package com.cafe.ordercenter.model.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.ordercenter.model.query
 * @Author: zhouboyi
 * @Date: 2025/4/30 16:11
 * @Description: 商品评论消息体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GoodsReviewMessage", description = "商品评论消息体")
public class GoodsReviewMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    private Long skuId;

    @ApiModelProperty(value = "评分 (1-5分)")
    private Integer rating;
}
