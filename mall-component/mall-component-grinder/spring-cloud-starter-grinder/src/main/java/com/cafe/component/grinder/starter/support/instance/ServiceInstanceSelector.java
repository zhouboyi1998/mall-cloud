package com.cafe.component.grinder.starter.support.instance;

import com.cafe.component.grinder.core.exception.GrinderException;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.support.instance
 * @Author: zhouboyi
 * @Date: 2025/7/7 10:42
 * @Description: 服务实例选择器
 */
public interface ServiceInstanceSelector {

    /**
     * 获取所有服务实例
     *
     * @param serviceId 服务ID
     * @return 服务实例列表
     */
    List<ServiceInstance> getAll(String serviceId) throws GrinderException;

    /**
     * 选择一个服务实例
     *
     * @param serviceId 服务ID
     * @return 服务实例
     */
    ServiceInstance selectOne(String serviceId) throws GrinderException;
}
