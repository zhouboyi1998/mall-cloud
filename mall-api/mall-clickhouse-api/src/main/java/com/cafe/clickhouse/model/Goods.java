package com.cafe.clickhouse.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.clickhouse.model
 * @Author: zhouboyi
 * @Date: 2024/3/15 15:59
 * @Description: ClickHouse 商品实体模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Goods", description = "ClickHouse 商品实体模型")
@TableName("mall_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long spuId;

    @ApiModelProperty(value = "SKU 原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "SKU 折扣价")
    private BigDecimal discountPrice;

    @ApiModelProperty(value = "SKU 秒杀价")
    private BigDecimal seckillPrice;

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "库存单位")
    private String unit;

    @ApiModelProperty(value = "销量")
    private Integer saleVolume;

    @ApiModelProperty(value = "SKU 图片")
    private String image;

    @ApiModelProperty(value = "SKU 图片列表")
    private String imageList;

    @ApiModelProperty(value = "SKU 视频")
    private String video;

    @ApiModelProperty(value = "SKU 规格")
    private String specification;

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime launchTime;

    @ApiModelProperty(value = "品牌ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long brandId;

    @ApiModelProperty(value = "分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "SPU 说明")
    private String caption;

    @ApiModelProperty(value = "SPU 详细介绍")
    private String intro;

    @ApiModelProperty(value = "评论数")
    private Integer commentVolume;
}
