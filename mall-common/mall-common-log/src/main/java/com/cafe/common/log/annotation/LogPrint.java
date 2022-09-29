package com.cafe.common.log.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.log.annotation
 * @Author: zhouboyi
 * @Date: 2022/9/16 9:19
 * @Description: 接口日志打印注解
 */
// 生成 Java 文档时, 文档只会记录有 @Documented 修饰的注解
@Documented
// 注解保留策略, RUNTIME: 一直保留到运行阶段 (JVM 加载 .class 文件后, 注解仍然保留)
@Retention(RetentionPolicy.RUNTIME)
// 注解添加的位置
@Target(ElementType.METHOD)
public @interface LogPrint {

    @AliasFor("description")
    String value() default "";

    @AliasFor("value")
    String description() default "";
}
