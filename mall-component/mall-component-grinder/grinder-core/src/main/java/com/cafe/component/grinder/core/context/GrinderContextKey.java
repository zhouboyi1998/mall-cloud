package com.cafe.component.grinder.core.context;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.context
 * @Author: zhouboyi
 * @Date: 2025/6/24 15:54
 * @Description: Grinder 上下文 Key
 */
public interface GrinderContextKey {

    /**
     * HttpServletRequest
     */
    String REQUEST = "request";

    /**
     * HttpServletResponse
     */
    String RESPONSE = "response";

    /**
     * 异常信息
     */
    String THROWABLE = "throwable";

    /**
     * 是否跳过路由转发
     */
    String SKIP_ROUTE = "skip_route";

    /**
     * 路由ID
     */
    String ROUTE_ID = "route_id";

    /**
     * 路由完整路径
     */
    String ROUTE_FULL_PATH = "route_full_path";

    /**
     * 路由前缀
     */
    String ROUTE_PREFIX = "route_prefix";

    /**
     * 路由真实路径
     */
    String ROUTE_PATH = "route_path";
}
