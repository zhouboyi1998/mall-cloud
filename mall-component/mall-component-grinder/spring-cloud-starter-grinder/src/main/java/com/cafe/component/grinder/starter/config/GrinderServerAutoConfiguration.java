package com.cafe.component.grinder.starter.config;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.component.grinder.core.filter.GrinderFilter;
import com.cafe.component.grinder.core.filter.support.GrinderFilterHolder;
import com.cafe.component.grinder.core.servlet.GrinderServlet;
import com.cafe.component.grinder.core.servlet.GrinderServletFilter;
import com.cafe.component.grinder.core.servlet.InitParameterKey;
import com.cafe.component.grinder.starter.property.GrinderProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.config
 * @Author: zhouboyi
 * @Date: 2025/6/20 17:37
 * @Description: Grinder 网关服务端自动装配配置类
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(value = GrinderProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(value = {GrinderServlet.class, GrinderServletFilter.class})
public class GrinderServerAutoConfiguration {

    /**
     * Grinder 配置
     */
    private final GrinderProperties grinderProperties;

    /**
     * Grinder 过滤器持有者
     */
    private final GrinderFilterHolder grinderFilterHolder = GrinderFilterHolder.instance();

    /**
     * Grinder 过滤器列表
     */
    private final List<GrinderFilter> grinderFilters;

    /**
     * Grinder Servlet
     */
    @Bean
    @ConditionalOnMissingBean(name = "grinderServlet")
    @ConditionalOnProperty(value = "grinder.filter-chain-deployment", havingValue = "SERVLET", matchIfMissing = true)
    public ServletRegistrationBean<GrinderServlet> grinderServlet() {
        return new ServletRegistrationBean<>(new GrinderServlet(), grinderProperties.getServletPatterns());
    }

    /**
     * Grinder ServletFilter
     */
    @Bean
    @ConditionalOnMissingBean(name = "grinderServletFilter")
    @ConditionalOnProperty(value = "grinder.filter-chain-deployment", havingValue = "SERVLET_FILTER")
    public FilterRegistrationBean<GrinderServletFilter> grinderServletFilter() {
        FilterRegistrationBean<GrinderServletFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setUrlPatterns(Arrays.asList(grinderProperties.getServletPatterns()));
        filterRegistration.setFilter(new GrinderServletFilter());
        filterRegistration.setOrder(Integer.MAX_VALUE);
        filterRegistration.addInitParameter(InitParameterKey.BUFFER_REQUESTS, StringConstant.FALSE);
        return filterRegistration;
    }

    /**
     * 加载 Grinder 过滤器链
     */
    @PostConstruct
    public void initFilters() {
        log.info("Grinder filters init start!");
        grinderFilterHolder.putAll(grinderFilters);
    }

    /**
     * 销毁 Grinder 过滤器链
     */
    @PreDestroy
    public void destroyFilters() {
        log.info("Grinder filters destroy start!");
        grinderFilterHolder.removeAll();
    }
}
