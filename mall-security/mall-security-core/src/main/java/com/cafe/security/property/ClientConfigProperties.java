package com.cafe.security.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.property
 * @Author: zhouboyi
 * @Date: 2022/5/15 18:53
 * @Description: 客户端详细信息配置
 */
@Component
@ConfigurationProperties(prefix = "client-config")
public class ClientConfigProperties {

    private List<ClientDetail> clientDetails;

    public List<ClientDetail> getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(List<ClientDetail> clientDetails) {
        this.clientDetails = clientDetails;
    }
}
