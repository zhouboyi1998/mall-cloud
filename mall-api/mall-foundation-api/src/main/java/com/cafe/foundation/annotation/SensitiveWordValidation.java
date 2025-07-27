package com.cafe.foundation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.annotation
 * @Author: zhouboyi
 * @Date: 2025/7/28 22:37
 * @Description: 方法参数敏感词校验注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface SensitiveWordValidation {

    String textFieldPath();
}
