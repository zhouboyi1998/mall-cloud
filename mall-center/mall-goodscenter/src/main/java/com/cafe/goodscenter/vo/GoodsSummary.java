package com.cafe.goodscenter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter.vo
 * @Author: zhouboyi
 * @Date: 2024/8/4 21:17
 * @Description: 商品概要
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GoodsSummary", description = "商品概要")
public class GoodsSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
    private Long spuId;

    @ApiModelProperty(value = "SKU 原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "SKU 折扣价")
    private BigDecimal discountPrice;

    @ApiModelProperty(value = "SKU 秒杀价")
    private BigDecimal seckillPrice;

    @ApiModelProperty(value = "SKU 图片")
    private String image;
}
