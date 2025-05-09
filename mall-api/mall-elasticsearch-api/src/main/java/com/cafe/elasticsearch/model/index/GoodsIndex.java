package com.cafe.elasticsearch.model.index;

import com.cafe.common.constant.elasticsearch.ElasticSearchConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.model.index
 * @Author: zhouboyi
 * @Date: 2022/10/11 11:42
 * @Description: 商品全文索引
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GoodsIndex", description = "商品全文索引")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Document(indexName = ElasticSearchConstant.Goods.INDEX)
public class GoodsIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    @Field(type = FieldType.Text)
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
    @Field(type = FieldType.Keyword)
    private Long spuId;

    @ApiModelProperty(value = "SPU 名称")
    @Field(type = FieldType.Text)
    private String spuName;

    @ApiModelProperty(value = "品牌ID")
    @Field(type = FieldType.Keyword)
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    @Field(type = FieldType.Text)
    private String brandName;

    @ApiModelProperty(value = "分类ID")
    @Field(type = FieldType.Keyword)
    private Long categoryId;

    @ApiModelProperty(value = "分类名称")
    @Field(type = FieldType.Text)
    private String categoryName;

    @ApiModelProperty(value = "店铺ID")
    @Field(type = FieldType.Keyword)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @Field(type = FieldType.Text)
    private String shopName;

    @ApiModelProperty(value = "SKU 规格")
    @Field(type = FieldType.Text)
    private String specification;

    @ApiModelProperty(value = "SKU 原价")
    @Field(type = FieldType.Keyword)
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "SKU 折扣价")
    @Field(type = FieldType.Keyword)
    private BigDecimal discountPrice;

    @ApiModelProperty(value = "SKU 秒杀价")
    @Field(type = FieldType.Keyword)
    private BigDecimal seckillPrice;

    @ApiModelProperty(value = "销量")
    @Field(type = FieldType.Keyword)
    private Integer sale;

    @ApiModelProperty(value = "总评论数")
    @Field(type = FieldType.Keyword)
    private Integer totalReview;

    @ApiModelProperty(value = "好评数")
    @Field(type = FieldType.Keyword)
    private Integer goodReview;

    @ApiModelProperty(value = "中评数")
    @Field(type = FieldType.Keyword)
    private Integer mediumReview;

    @ApiModelProperty(value = "差评数")
    @Field(type = FieldType.Keyword)
    private Integer badReview;

    @ApiModelProperty(value = "上架时间")
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime launchTime;
}
