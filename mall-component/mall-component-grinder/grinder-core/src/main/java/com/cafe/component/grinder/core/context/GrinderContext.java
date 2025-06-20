package com.cafe.component.grinder.core.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.core.context
 * @Author: zhouboyi
 * @Date: 2025/6/23 15:28
 * @Description: Grinder 上下文
 */
public class GrinderContext extends ConcurrentHashMap<String, Object> {

    /**
     * 上下文的 class 对象
     */
    protected static Class<? extends GrinderContext> clazz = GrinderContext.class;

    /**
     * 初始化 ThreadLocal
     */
    protected static final ThreadLocal<? extends GrinderContext> threadLocal = ThreadLocal.withInitial(() -> {
        try {
            return clazz.newInstance();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    });

    public GrinderContext() {
        super();
    }

    /**
     * 获取当前线程的 Grinder 上下文
     *
     * @return Grinder 上下文
     */
    public static GrinderContext currentContext() {
        return threadLocal.get();
    }

    /**
     * 销毁当前线程的 Grinder 上下文
     */
    public void unset() {
        threadLocal.remove();
    }

    /**
     * 设置 Grinder 上下文
     *
     * @param key   上下文键
     * @param value 上下文值
     */
    public void set(String key, Object value) {
        if (value != null) {
            this.put(key, value);
        } else {
            // 如果值为 null, 移除该上下文键
            this.remove(key);
        }
    }

    /**
     * 判断是否跳过路由转发
     *
     * @return 判断结果 (true: 跳过, false: 不跳过)
     */
    public boolean skipRoute() {
        return Boolean.TRUE.equals(this.get(GrinderContextKey.SKIP_ROUTE));
    }

    /**
     * 获取 HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        return (HttpServletRequest) this.get(GrinderContextKey.REQUEST);
    }

    /**
     * 获取 HttpServletResponse
     *
     * @return HttpServletResponse
     */
    public HttpServletResponse getResponse() {
        return (HttpServletResponse) this.get(GrinderContextKey.RESPONSE);
    }
}
