package com.cafe.component.grinder.core.servlet.http;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.servlet.http
 * @Author: zhouboyi
 * @Date: 2025/6/23 17:58
 * @Description: HttpServletRequest 包装类
 */
@Getter
public class GrinderServletRequestWrapper extends HttpServletRequestWrapper {

    private final HttpServletRequest request;

    public GrinderServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
}
