package com.cafe.goodscenter.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter.model.vo
 * @Author: zhouboyi
 * @Date: 2024/8/4 23:49
 * @Description: 商品子项详情
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GoodsItem", description = "商品子项详情")
public class GoodsItem implements Serializable {

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

    @ApiModelProperty(value = "SKU 规格")
    private String specification;

    @ApiModelProperty(value = "SKU 图片")
    private String image;

    @ApiModelProperty(value = "SKU 图片列表")
    private String imageList;

    @ApiModelProperty(value = "SKU 视频")
    private String video;
}
