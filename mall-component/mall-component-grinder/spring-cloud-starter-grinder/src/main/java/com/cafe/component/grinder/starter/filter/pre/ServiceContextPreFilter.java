package com.cafe.component.grinder.starter.filter.pre;

import com.cafe.component.grinder.core.context.GrinderContext;
import com.cafe.component.grinder.core.context.GrinderContextKey;
import com.cafe.component.grinder.core.exception.GrinderException;
import com.cafe.component.grinder.core.filter.GrinderPreRouteFilter;
import com.cafe.component.grinder.starter.support.route.Route;
import com.cafe.component.grinder.starter.support.route.RouteParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UrlPathHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.filter.pre
 * @Author: zhouboyi
 * @Date: 2025/6/25 17:57
 * @Description: 服务上下文预加载前置过滤器
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ServiceContextPreFilter extends GrinderPreRouteFilter {

    private static final String FILTER_NAME = ServiceContextPreFilter.class.getSimpleName();

    private final UrlPathHelper urlPathHelper = new UrlPathHelper();

    private final RouteParser routeParser;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String filterName() {
        return FILTER_NAME;
    }

    @Override
    public boolean supports() {
        return true;
    }

    @Override
    public Object run() throws GrinderException {
        log.info("{} {} run.", this.filterType(), this.filterName());
        // 获取上下文
        GrinderContext context = GrinderContext.currentContext();
        // 获取请求路径
        String requestURI = this.urlPathHelper.getPathWithinApplication(context.getRequest());
        if (this.insecurePath(requestURI)) {
            throw new RuntimeException("Path cannot contain insecure segments: " + requestURI);
        }
        // 解析路由
        Route route = this.routeParser.parse(requestURI);
        context.set(GrinderContextKey.ROUTE_ID, route.getId());
        context.set(GrinderContextKey.ROUTE_FULL_PATH, route.getFullPath());
        context.set(GrinderContextKey.ROUTE_PREFIX, route.getPrefix());
        context.set(GrinderContextKey.ROUTE_PATH, route.getPath());

        return route;
    }

    private boolean insecurePath(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        } else {
            if (path.contains("%")) {
                try {
                    path = URLDecoder.decode(path, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    log.error("URL decode error! message -> {}", e.getMessage(), e);
                }
            }
            return this.isInsecurePath(path) || this.isInsecurePath(this.urlPathHelper.removeSemicolonContent(path));
        }
    }

    private boolean isInsecurePath(String path) {
        if (path.contains(":/")) {
            String relativePath = path.charAt(0) == '/' ? path.substring(1) : path;
            if (ResourceUtils.isUrl(relativePath) || relativePath.startsWith("url:")) {
                if (log.isWarnEnabled()) {
                    log.warn("Path represents URL or has \"url:\" prefix: [{}]", path);
                }
                return true;
            }
        }

        if (path.contains("../")) {
            if (log.isWarnEnabled()) {
                log.warn("Path contains \"../\"");
            }
            return true;
        } else if (path.contains("..\\")) {
            if (log.isWarnEnabled()) {
                log.warn("Path contains \"..\\\"");
            }
            return true;
        } else {
            return false;
        }
    }
}
