package com.cafe.component.grinder.starter.support.instance;

import com.cafe.component.grinder.core.exception.GrinderException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.support.instance
 * @Author: zhouboyi
 * @Date: 2025/7/7 10:55
 * @Description:
 */
@RequiredArgsConstructor
public abstract class AbstractServiceInstanceSelector implements ServiceInstanceSelector {

    /**
     * 服务发现客户端
     */
    private final DiscoveryClient discoveryClient;

    @Override
    public List<ServiceInstance> getAll(String serviceId) throws GrinderException {
        List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceId);
        if (instanceList.isEmpty()) {
            throw new GrinderException(500, "service [" + serviceId + "] list is empty.");
        }
        return instanceList;
    }
}
