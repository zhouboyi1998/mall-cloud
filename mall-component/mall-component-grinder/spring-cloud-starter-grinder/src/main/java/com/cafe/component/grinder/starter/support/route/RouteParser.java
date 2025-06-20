package com.cafe.component.grinder.starter.support.route;

import com.cafe.common.constant.pool.StringConstant;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.support.route
 * @Author: zhouboyi
 * @Date: 2025/6/25 18:13
 * @Description: 路由解析器
 */
@Component
public class RouteParser {

    /**
     * 解析路由
     *
     * @param requestURI 请求 URI
     * @return 路由信息
     */
    public Route parse(String requestURI) {
        String[] split = requestURI.split(StringConstant.SLASH, 3);
        // 如果没有获取到服务ID, 返回 null
        if (split.length < 2 || !StringUtils.hasText(split[1])) {
            return null;
        }
        return Route.builder()
            .id(split[1])
            .fullPath(requestURI)
            .prefix(StringConstant.SLASH + split[1])
            .path(split.length == 3 && StringUtils.hasText(split[2]) ? StringConstant.SLASH + split[2] : StringConstant.SLASH)
            .build();
    }
}
