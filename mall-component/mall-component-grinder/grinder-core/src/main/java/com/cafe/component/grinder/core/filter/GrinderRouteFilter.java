package com.cafe.component.grinder.core.filter;

import com.cafe.component.grinder.core.filter.support.GrinderFilterType;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.filter
 * @Author: zhouboyi
 * @Date: 2025/6/19 18:33
 * @Description: Grinder 路由过滤器抽象类
 */
public abstract class GrinderRouteFilter extends GrinderFilter {

    @Override
    public GrinderFilterType filterType() {
        return GrinderFilterType.ROUTE;
    }
}
