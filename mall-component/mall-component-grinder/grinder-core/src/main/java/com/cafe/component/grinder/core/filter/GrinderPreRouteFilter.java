package com.cafe.component.grinder.core.filter;

import com.cafe.component.grinder.core.filter.support.GrinderFilterType;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.filter
 * @Author: zhouboyi
 * @Date: 2025/6/19 18:32
 * @Description: Grinder 前置过滤器抽象类
 */
public abstract class GrinderPreRouteFilter extends GrinderFilter {

    @Override
    public GrinderFilterType filterType() {
        return GrinderFilterType.PRE_ROUTE;
    }
}
