package com.cafe.solr.model.index;

import com.cafe.common.constant.solr.SolrConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @Package: com.cafe.solr.model.index
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:42
 * @Description: 商品全文索引
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GoodsIndex", description = "商品全文索引")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@SolrDocument(collection = SolrConstant.Goods.INDEX)
public class GoodsIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    @Id
    @Field
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    @Field
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
    @Field
    private Long spuId;

    @ApiModelProperty(value = "SPU 名称")
    @Field
    private String spuName;

    @ApiModelProperty(value = "品牌ID")
    @Field
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    @Field
    private String brandName;

    @ApiModelProperty(value = "分类ID")
    @Field
    private Long categoryId;

    @ApiModelProperty(value = "分类名称")
    @Field
    private String categoryName;

    @ApiModelProperty(value = "店铺ID")
    @Field
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @Field
    private String shopName;

    @ApiModelProperty(value = "SKU 规格")
    @Field
    private String specification;

    @ApiModelProperty(value = "SKU 原价")
    @Field
    private Double originalPrice;

    @ApiModelProperty(value = "SKU 折扣价")
    @Field
    private Double discountPrice;

    @ApiModelProperty(value = "SKU 秒杀价")
    @Field
    private Double seckillPrice;

    @ApiModelProperty(value = "销量")
    @Field
    private Integer sale;

    @ApiModelProperty(value = "总评论数")
    @Field
    private Integer totalComment;

    @ApiModelProperty(value = "好评数")
    @Field
    private Integer goodComment;

    @ApiModelProperty(value = "中评数")
    @Field
    private Integer mediumComment;

    @ApiModelProperty(value = "差评数")
    @Field
    private Integer badComment;

    @ApiModelProperty(value = "上架时间")
    @Field
    private LocalDateTime launchTime;
}
