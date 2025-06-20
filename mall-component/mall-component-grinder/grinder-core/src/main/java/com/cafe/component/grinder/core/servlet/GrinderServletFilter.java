package com.cafe.component.grinder.core.servlet;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.component.grinder.core.GrinderRunner;
import com.cafe.component.grinder.core.context.GrinderContext;
import com.cafe.component.grinder.core.context.GrinderContextKey;
import com.cafe.component.grinder.core.exception.GrinderException;
import lombok.NoArgsConstructor;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.servlet
 * @Author: zhouboyi
 * @Date: 2025/6/24 18:01
 * @Description:
 */
@NoArgsConstructor
public class GrinderServletFilter implements Filter {

    private GrinderRunner grinderRunner;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String bufferRequestsStr = filterConfig.getInitParameter(InitParameterKey.BUFFER_REQUESTS);
        boolean bufferRequests = Objects.equals(bufferRequestsStr, StringConstant.TRUE);
        this.grinderRunner = new GrinderRunner(bufferRequests);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            this.grinderRunner.init((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);

            try {
                this.preRouting();
            } catch (GrinderException e) {
                this.error(e);
                this.postRouting();
                return;
            }

            if (GrinderContext.currentContext().skipRoute()) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            try {
                this.routing();
            } catch (GrinderException e) {
                this.error(e);
                this.postRouting();
                return;
            }

            try {
                this.postRouting();
            } catch (GrinderException e) {
                this.error(e);
            }

        } catch (Throwable t) {
            this.error(new GrinderException(500, "Unhandled throwable in servlet filter [" + t.getClass().getName() + "]", t));
        } finally {
            GrinderContext.currentContext().unset();
        }
    }

    void preRouting() throws GrinderException {
        this.grinderRunner.preRoute();
    }

    void routing() throws GrinderException {
        this.grinderRunner.route();
    }

    void postRouting() throws GrinderException {
        this.grinderRunner.postRoute();
    }

    void error(GrinderException e) {
        GrinderContext.currentContext().set(GrinderContextKey.THROWABLE, e);
        this.grinderRunner.error();
    }
}
