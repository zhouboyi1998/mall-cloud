package com.cafe.component.grinder.core.servlet;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.component.grinder.core.GrinderRunner;
import com.cafe.component.grinder.core.context.GrinderContext;
import com.cafe.component.grinder.core.context.GrinderContextKey;
import com.cafe.component.grinder.core.exception.GrinderException;
import lombok.NoArgsConstructor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.servlet
 * @Author: zhouboyi
 * @Date: 2025/6/19 18:06
 * @Description: Grinder Servlet
 */
@NoArgsConstructor
public class GrinderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private GrinderRunner grinderRunner;

    /**
     * 初始化 Servlet
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String bufferRequestsStr = config.getInitParameter(InitParameterKey.BUFFER_REQUESTS);
        boolean bufferRequests = Objects.equals(bufferRequestsStr, StringConstant.TRUE);
        this.grinderRunner = new GrinderRunner(bufferRequests);
    }

    /**
     * 处理请求
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            grinderRunner.init((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);

            try {
                this.preRoute();
            } catch (GrinderException e) {
                this.error(e);
                this.postRoute();
                return;
            }

            try {
                this.route();
            } catch (GrinderException e) {
                this.error(e);
                this.postRoute();
                return;
            }

            try {
                this.postRoute();
            } catch (GrinderException e) {
                this.error(e);
            }

        } catch (Throwable t) {
            this.error(new GrinderException(500, "Unhandled throwable in servlet [" + t.getClass().getName() + "]", t));
        } finally {
            GrinderContext.currentContext().unset();
        }
    }

    /**
     * 执行前置过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    void preRoute() throws GrinderException {
        this.grinderRunner.preRoute();
    }

    /**
     * 执行路由过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    void route() throws GrinderException {
        this.grinderRunner.route();
    }

    /**
     * 执行后置过滤器
     *
     * @throws GrinderException Grinder 异常信息
     */
    void postRoute() throws GrinderException {
        this.grinderRunner.postRoute();
    }

    /**
     * 执行错误处理过滤器
     */
    void error(GrinderException e) {
        GrinderContext.currentContext().set(GrinderContextKey.THROWABLE, e);
        this.grinderRunner.error();
    }
}
