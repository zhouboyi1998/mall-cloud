package com.cafe.starter.boot.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.boot.condition
 * @Author: zhouboyi
 * @Date: 2025/7/29 10:53
 * @Description: 服务条件判断注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Conditional(value = OnServiceCondition.class)
public @interface ConditionalOnService {

    /**
     * 包含的服务列表
     */
    String[] includeServices() default {};

    /**
     * 排除的服务列表
     */
    String[] excludeServices() default {};
}
