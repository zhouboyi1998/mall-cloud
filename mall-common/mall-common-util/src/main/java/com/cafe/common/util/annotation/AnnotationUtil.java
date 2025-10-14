package com.cafe.common.util.annotation;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

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
     * @param fieldGetter     注解字段值获取函数
     * @param <A>             注解类对象
     * @param <R>             字段值类型
     * @return 字段值
     */
    public static <A extends Annotation, R> R findAnnotationField(Class<?> clazz, Class<A> annotationClazz, Function<A, R> fieldGetter) {
        return findAnnotationField(clazz, annotationClazz, fieldGetter, () -> null);
    }

    /**
     * 获取类上的注解的字段值
     *
     * @param clazz           类对象
     * @param annotationClazz 注解类对象
     * @param fieldGetter     注解字段值获取函数
     * @param defaultSupplier 默认值获取函数
     * @param <A>             注解类对象
     * @param <R>             字段值类型
     * @return 字段值
     */
    public static <A extends Annotation, R> R findAnnotationField(Class<?> clazz, Class<A> annotationClazz, Function<A, R> fieldGetter, Supplier<R> defaultSupplier) {
        // 必须使用 Spring AnnotationUtils 工具类获取注解, 否则无法获取注解字段别名 (@AliasFor 注解由 Spring 提供)
        return Optional.ofNullable(AnnotationUtils.findAnnotation(clazz, annotationClazz)).map(fieldGetter).orElseGet(defaultSupplier);
    }

    /**
     * 获取字段上的注解的字段值
     *
     * @param field           字段对象
     * @param annotationClazz 注解类对象
     * @param fieldGetter     注解字段值获取函数
     * @param <A>             注解类对象
     * @param <R>             字段值类型
     * @return 字段值
     */
    public static <A extends Annotation, R> R findAnnotationField(Field field, Class<A> annotationClazz, Function<A, R> fieldGetter) {
        return findAnnotationField(field, annotationClazz, fieldGetter, () -> null);
    }

    /**
     * 获取字段上的注解的字段值
     *
     * @param field           字段对象
     * @param annotationClazz 注解类对象
     * @param fieldGetter     注解字段值获取函数
     * @param defaultSupplier 默认值获取函数
     * @param <A>             注解类对象
     * @param <R>             字段值类型
     * @return 字段值
     */
    public static <A extends Annotation, R> R findAnnotationField(Field field, Class<A> annotationClazz, Function<A, R> fieldGetter, Supplier<R> defaultSupplier) {
        // 必须使用 Spring AnnotationUtils 工具类获取注解, 否则无法获取注解字段别名 (@AliasFor 注解由 Spring 提供)
        return Optional.ofNullable(AnnotationUtils.findAnnotation(field, annotationClazz)).map(fieldGetter).orElseGet(defaultSupplier);
    }

    /**
     * 获取方法上的注解的字段值
     *
     * @param method          方法对象
     * @param annotationClazz 注解类对象
     * @param fieldGetter     注解字段值获取函数
     * @param <A>             注解类对象
     * @param <R>             字段值类型
     * @return 字段值
     */
    public static <A extends Annotation, R> R findAnnotationField(Method method, Class<A> annotationClazz, Function<A, R> fieldGetter) {
        return findAnnotationField(method, annotationClazz, fieldGetter, () -> null);
    }

    /**
     * 获取方法上的注解的字段值
     *
     * @param method          方法对象
     * @param annotationClazz 注解类对象
     * @param fieldGetter     注解字段值获取函数
     * @param defaultSupplier 默认值获取函数
     * @param <A>             注解类对象
     * @param <R>             字段值类型
     * @return 字段值
     */
    public static <A extends Annotation, R> R findAnnotationField(Method method, Class<A> annotationClazz, Function<A, R> fieldGetter, Supplier<R> defaultSupplier) {
        // 必须使用 Spring AnnotationUtils 工具类获取注解, 否则无法获取注解字段别名 (@AliasFor 注解由 Spring 提供)
        return Optional.ofNullable(AnnotationUtils.findAnnotation(method, annotationClazz)).map(fieldGetter).orElseGet(defaultSupplier);
    }
}
