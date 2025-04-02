package com.cafe.elasticsearch.model.index;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

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
@Document(indexName = "goods_index")
public class GoodsIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    @Field(name = "skuName")
    private String skuName;

    @ApiModelProperty(value = "SPU 名称")
    @Field(name = "spuName",type = FieldType.Text, analyzer = "ik_max_word")
    private String spuName;

    @ApiModelProperty(value = "品牌名称")
    @Field(name = "brandName")
    private String brandName;

    @ApiModelProperty(value = "分类名称")
    @Field(name = "categoryName")
    private String categoryName;

    @ApiModelProperty(value = "SKU 规格")
    @Field(name = "specification")
    private String specification;
}
