package com.cafe.component.grinder.starter.filter.route;

import com.cafe.component.grinder.core.context.GrinderContext;
import com.cafe.component.grinder.core.context.GrinderContextKey;
import com.cafe.component.grinder.core.exception.GrinderException;
import com.cafe.component.grinder.core.filter.GrinderRouteFilter;
import com.cafe.component.grinder.starter.support.http.HttpInvoker;
import com.cafe.component.grinder.starter.support.instance.ServiceInstanceSelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.filter.route
 * @Author: zhouboyi
 * @Date: 2025/6/25 15:59
 * @Description: 服务实例路由过滤器
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ServiceInstanceRouteFilter extends GrinderRouteFilter {

    private static final String FILTER_NAME = ServiceInstanceRouteFilter.class.getSimpleName();

    /**
     * 服务实例选择器
     */
    private final ServiceInstanceSelector serviceInstanceSelector;

    /**
     * HTTP 调用器
     */
    private final HttpInvoker httpInvoker;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String filterName() {
        return FILTER_NAME;
    }

    @Override
    public boolean supports() {
        return true;
    }

    @Override
    public Object run() throws GrinderException {
        log.info("{} {} run.", this.filterType(), this.filterName());
        // 获取上下文
        GrinderContext context = GrinderContext.currentContext();
        // 获取服务ID、请求路径
        String serviceId = (String) context.get(GrinderContextKey.ROUTE_ID);
        String path = (String) context.get(GrinderContextKey.ROUTE_PATH);

        // 获取服务实例
        ServiceInstance serviceInstance = serviceInstanceSelector.selectOne(serviceId);
        // 组装目标请求 URI
        String targetURI = serviceInstance.getUri() + path;

        // 获取 HttpServletRequest、HttpServletResponse
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        try {
            // 发起调用
            httpInvoker.invoke(request, response, targetURI);
        } catch (Exception e) {
            log.error("service [{}] route error, message -> {}", serviceId, e.getMessage(), e);
            throw new GrinderException(500, "service [" + serviceId + "] route error.");
        }

        return null;
    }
}
