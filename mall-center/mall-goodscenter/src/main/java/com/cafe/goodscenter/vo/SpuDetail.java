package com.cafe.goodscenter.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goodscenter.vo
 * @Author: zhouboyi
 * @Date: 2024/8/4 21:17
 * @Description: SPU 详情
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SpuDetail", description = "SPU 详情")
public class SpuDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SPU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "SPU 名称")
    private String spuName;

    @ApiModelProperty(value = "品牌ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopName;

    @ApiModelProperty(value = "SPU 说明")
    private String caption;

    @ApiModelProperty(value = "SKU 列表")
    List<SkuDetail> skuList;
}
