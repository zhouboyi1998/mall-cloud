package com.cafe.security.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.model
 * @Author: zhouboyi
 * @Date: 2023/2/22 16:58
 * @Description: 图片验证码模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Captcha", description = "图片验证码模型")
public class Captcha implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片验证码唯一标识")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long key;

    @ApiModelProperty(value = "Base64 编码图片验证码")
    private String image;
}
