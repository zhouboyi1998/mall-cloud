package com.cafe.component.grinder.starter.support.instance.random;

import com.cafe.component.grinder.core.exception.GrinderException;
import com.cafe.component.grinder.starter.support.instance.AbstractServiceInstanceSelector;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.support.instance.random
 * @Author: zhouboyi
 * @Date: 2025/7/7 10:43
 * @Description: Random 服务实例选择器
 */
public class RandomServiceInstanceSelector extends AbstractServiceInstanceSelector {

    public RandomServiceInstanceSelector(DiscoveryClient discoveryClient) {
        super(discoveryClient);
    }

    @Override
    public ServiceInstance selectOne(String serviceId) throws GrinderException {
        List<ServiceInstance> instanceList = getAll(serviceId);
        return instanceList.get(ThreadLocalRandom.current().nextInt(instanceList.size()));
    }
}
