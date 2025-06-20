package com.cafe.component.grinder.core.filter;

import com.cafe.component.grinder.core.filter.support.GrinderFilterType;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.filter
 * @Author: zhouboyi
 * @Date: 2025/6/23 16:03
 * @Description:
 */
public abstract class GrinderErrorFilter extends GrinderFilter {

    @Override
    public GrinderFilterType filterType() {
        return GrinderFilterType.ERROR;
    }
}
