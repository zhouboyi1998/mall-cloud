package com.cafe.solr.index;

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

/**
 * @Project: mall-cloud
 * @Package: com.cafe.solr.index
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
    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    @Field
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    @Field
    private String skuName;

    @ApiModelProperty(value = "SPU 名称")
    @Field
    private String spuName;

    @ApiModelProperty(value = "品牌名称")
    @Field
    private String brandName;

    @ApiModelProperty(value = "分类名称")
    @Field
    private String categoryName;

    @ApiModelProperty(value = "SKU 规格")
    @Field
    private String specification;
}
