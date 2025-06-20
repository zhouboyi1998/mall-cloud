package com.cafe.component.grinder.core.servlet.http;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.servlet.http
 * @Author: zhouboyi
 * @Date: 2025/6/23 17:59
 * @Description: HttpServletResponse 包装类
 */
public class GrinderServletResponseWrapper extends HttpServletResponseWrapper {

    public GrinderServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }
}
