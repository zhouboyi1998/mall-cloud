package com.cafe.common.util.annotation;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.annotation
 * @Author: zhouboyi
 * @Date: 2024/7/19 10:18
 * @Description: 注解工具类
 */
public class AnnotationUtil {

    /**
     * 获取类上的注解的字段值
     *
     * @param clazz           类对象
     * @param annotationClazz 注解类对象
     * @param function        注解字段值获取函数
     * @param <A>             注解类对象
     * @param <R>             字段值类型
     * @return 字段值
     */
    public static <A extends Annotation, R> R findAnnotationField(Class<?> clazz, Class<A> annotationClazz, Function<A, R> function) {
        // 必须使用 Spring AnnotationUtils 工具类获取注解, 否则无法获取注解字段别名 (@AliasFor 注解由 Spring 提供)
        A annotation = AnnotationUtils.findAnnotation(clazz, annotationClazz);
        return annotation != null ? function.apply(annotation) : null;
    }

    /**
     * 获取方法上的注解的字段值
     *
     * @param method          方法对象
     * @param annotationClazz 注解类对象
     * @param function        注解字段值获取函数
     * @param <A>             注解类对象
     * @param <R>             字段值类型
     * @return 字段值
     */
    public static <A extends Annotation, R> R findAnnotationField(Method method, Class<A> annotationClazz, Function<A, R> function) {
        // 必须使用 Spring AnnotationUtils 工具类获取注解, 否则无法获取注解字段别名 (@AliasFor 注解由 Spring 提供)
        A annotation = AnnotationUtils.findAnnotation(method, annotationClazz);
        return annotation != null ? function.apply(annotation) : null;
    }
}
