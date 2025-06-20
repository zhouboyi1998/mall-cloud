package com.cafe.component.grinder.core.filter;

import com.cafe.component.grinder.core.exception.GrinderException;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.filter
 * @Author: zhouboyi
 * @Date: 2025/6/19 18:27
 * @Description: Grinder 过滤器接口
 */
public interface IGrinderFilter {

    /**
     * 判断是否需要执行当前过滤器
     *
     * @return 判断结果 (true: 需要, false: 不需要)
     */
    boolean supports();

    /**
     * 过滤器运行逻辑
     *
     * @return 过滤器运行结果
     * @throws GrinderException Grinder 异常信息
     */
    Object run() throws GrinderException;
}
