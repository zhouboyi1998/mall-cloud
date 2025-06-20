package com.cafe.component.grinder.core.filter.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.filter.support
 * @Author: zhouboyi
 * @Date: 2025/6/20 15:54
 * @Description: Grinder 过滤器类型枚举
 */
@Getter
@AllArgsConstructor
public enum GrinderFilterType {

    /**
     * 前置过滤器
     */
    PRE_ROUTE,

    /**
     * 路由过滤器
     */
    ROUTE,

    /**
     * 后置过滤器
     */
    POST_ROUTE,

    /**
     * 错误处理过滤器
     */
    ERROR,
}
