package com.cafe.foundation.annotation;

import com.cafe.foundation.validator.SensitiveValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.annotation
 * @Author: zhouboyi
 * @Date: 2025/7/29 10:08
 * @Description: 敏感词校验注解
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Repeatable(value = Sensitive.List.class)
@Constraint(validatedBy = SensitiveValidator.class)
public @interface Sensitive {

    String message() default "Sensitive Word Found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Retention(value = RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Documented
    @interface List {
        Sensitive[] value();
    }
}
