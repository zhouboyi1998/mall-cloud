package com.cafe.component.grinder.core;

import com.cafe.component.grinder.core.context.GrinderContext;
import com.cafe.component.grinder.core.context.GrinderContextKey;
import com.cafe.component.grinder.core.exception.GrinderException;
import com.cafe.component.grinder.core.filter.support.GrinderFilterProcessor;
import com.cafe.component.grinder.core.servlet.http.GrinderServletRequestWrapper;
import com.cafe.component.grinder.core.servlet.http.GrinderServletResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core
 * @Author: zhouboyi
 * @Date: 2025/6/19 18:25
 * @Description: Grinder 运行器
 */
public class GrinderRunner {

    private final boolean bufferRequests;

    public GrinderRunner() {
        this.bufferRequests = true;
    }

    public GrinderRunner(boolean bufferRequests) {
        this.bufferRequests = bufferRequests;
    }

    /**
     * 初始化 Grinder 上下文
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    public void init(HttpServletRequest request, HttpServletResponse response) {
        GrinderContext context = GrinderContext.currentContext();
        if (this.bufferRequests) {
            context.put(GrinderContextKey.REQUEST, new GrinderServletRequestWrapper(request));
        } else {
            context.put(GrinderContextKey.REQUEST, request);
        }
        context.put(GrinderContextKey.RESPONSE, new GrinderServletResponseWrapper(response));
    }

    /**
     * 执行前置过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    public void preRoute() throws GrinderException {
        GrinderFilterProcessor.instance().preRoute();
    }

    /**
     * 执行路由过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    public void route() throws GrinderException {
        GrinderFilterProcessor.instance().route();
    }

    /**
     * 执行后置过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    public void postRoute() throws GrinderException {
        GrinderFilterProcessor.instance().postRoute();
    }

    /**
     * 执行错误处理过滤器
     */
    public void error() {
        GrinderFilterProcessor.instance().error();
    }
}
