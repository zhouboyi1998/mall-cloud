package com.cafe.component.grinder.starter.support.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.support.route
 * @Author: zhouboyi
 * @Date: 2025/6/25 18:09
 * @Description: 路由信息
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    /**
     * 路由ID
     */
    private String id;

    /**
     * 完整路径
     */
    private String fullPath;

    /**
     * 路由前缀
     */
    private String prefix;

    /**
     * 真实路径
     */
    private String path;
}
