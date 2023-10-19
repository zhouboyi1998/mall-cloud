package com.cafe.search.solr.model;

import com.cafe.common.constant.solr.SolrConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.search.solr.model
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:42
 * @Description: Solr 商品实体模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Goods", description = "Solr 商品实体模型")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@SolrDocument(collection = SolrConstant.Goods.INDEX)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    @Field
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    @Field
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @Field
    private Long spuId;

    @ApiModelProperty(value = "SKU 原价")
    @Field
    private Double originalPrice;

    @ApiModelProperty(value = "SKU 折扣价")
    @Field
    private Double discountPrice;

    @ApiModelProperty(value = "SKU 秒杀价")
    @Field
    private Double seckillPrice;

    @ApiModelProperty(value = "库存量")
    @Field
    private Integer stock;

    @ApiModelProperty(value = "库存单位")
    @Field
    private String unit;

    @ApiModelProperty(value = "销量")
    @Field
    private Integer saleVolume;

    @ApiModelProperty(value = "SKU 图片")
    @Field
    private String image;

    @ApiModelProperty(value = "SKU 图片列表")
    @Field
    private String imageList;

    @ApiModelProperty(value = "SKU 视频")
    @Field
    private String video;

    @ApiModelProperty(value = "SKU 规格")
    @Field
    private String specification;

    @ApiModelProperty(value = "上架时间")
    @Field
    private LocalDateTime launchTime;

    @ApiModelProperty(value = "品牌ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @Field
    private Long brandId;

    @ApiModelProperty(value = "分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @Field
    private Long categoryId;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @Field
    private Long shopId;

    @ApiModelProperty(value = "SPU 说明")
    @Field
    private String caption;

    @ApiModelProperty(value = "SPU 详细介绍")
    @Field
    private String intro;

    @ApiModelProperty(value = "评论数")
    @Field
    private Integer commentVolume;
}
