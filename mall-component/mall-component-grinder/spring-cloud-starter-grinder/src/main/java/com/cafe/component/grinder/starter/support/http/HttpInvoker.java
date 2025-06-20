package com.cafe.component.grinder.starter.support.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.support.http
 * @Author: zhouboyi
 * @Date: 2025/6/26 17:12
 * @Description: HTTP 调用器
 */
public interface HttpInvoker {

    /**
     * 发起调用
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param targetURI 目标 URI
     */
    void invoke(HttpServletRequest request, HttpServletResponse response, String targetURI) throws Exception;
}
