package com.cafe.foundation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.dto
 * @Author: zhouboyi
 * @Date: 2023/10/27 16:55
 * @Description: 区域传输模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AreaDTO", description = "区域传输模型")
public class AreaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "省份id")
    private String provinceId;

    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    @ApiModelProperty(value = "城市id")
    private String cityId;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "区县id")
    private String districtId;

    @ApiModelProperty(value = "区县名称")
    private String districtName;

    @ApiModelProperty(value = "邮政编码")
    private Integer postCode;
}
