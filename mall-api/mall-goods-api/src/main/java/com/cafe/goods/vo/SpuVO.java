package com.cafe.goods.vo;

import com.cafe.goods.model.Sku;
import com.cafe.goods.model.Spu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.vo
 * @Author: zhouboyi
 * @Date: 2024/8/4 22:49
 * @Description: SPU 视图模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SpuVO", description = "SPU 视图模型")
public class SpuVO extends Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU 列表")
    private List<Sku> skuList;
}
