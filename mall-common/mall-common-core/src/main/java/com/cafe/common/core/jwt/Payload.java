package com.cafe.common.core.jwt;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.jwt
 * @Author: zhouboyi
 * @Date: 2023/8/7 16:16
 * @Description: JWT 载荷
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Payload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 授权范围
     */
    private List<String> scope;

    /**
     * 过期时间 (时间戳类型)
     */
    private Long exp;

    /**
     * 权限列表
     */
    private List<String> authorities;

    /**
     * JWT 唯一身份标识
     */
    private String jti;

    /**
     * 客户端id
     */
    private String clientId;
}
