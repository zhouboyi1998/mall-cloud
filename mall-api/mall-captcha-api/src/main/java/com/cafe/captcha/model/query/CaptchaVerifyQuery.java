package com.cafe.captcha.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.model.query
 * @Author: zhouboyi
 * @Date: 2025/4/28 17:58
 * @Description: 校验图片验证码查询条件
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CaptchaVerifyQuery", description = "校验图片验证码查询条件")
public class CaptchaVerifyQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片验证码唯一标识")
    @NotNull(message = "图片验证码唯一标识不能为空")
    private Long key;

    @ApiModelProperty(value = "图片验证码文本")
    @NotBlank(message = "图片验证码文本不能为空")
    private String code;
}
