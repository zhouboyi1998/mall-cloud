package com.cafe.security.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.property
 * @Author: zhouboyi
 * @Date: 2022/5/15 18:53
 * @Description: 客户端配置
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "client")
public class ClientProperties {

    /**
     * 客户端详细信息配置列表
     */
    private List<Detail> details;

    @Getter
    @Setter
    public static class Detail {

        /**
         * 客户端id
         */
        private String clientId;

        /**
         * 授权模式
         */
        private String[] authorizedGrantTypes;

        /**
         * 授权范围
         */
        private String[] scopes;

        /**
         * 访问令牌过期时间
         */
        private Integer accessTokenValiditySeconds;

        /**
         * 刷新令牌过期时间
         */
        private Integer refreshTokenValiditySeconds;
    }
}
