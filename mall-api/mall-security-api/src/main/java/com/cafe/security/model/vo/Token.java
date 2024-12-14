package com.cafe.security.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.model.vo
 * @Author: zhouboyi
 * @Date: 2022/5/9 10:48
 * @Description: 认证令牌
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Token", description = "认证令牌")
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "访问令牌")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;

    @ApiModelProperty(value = "访问令牌头前缀")
    private String tokenPrefix;

    @ApiModelProperty(value = "有效时间 (秒)")
    private Integer expiresIn;
}
