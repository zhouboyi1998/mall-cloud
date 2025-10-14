package com.cafe.common.util.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.annotation
 * @Author: zhouboyi
 * @Date: 2025/10/14 11:50
 * @Description:
 */
class AnnotationUtilTest {

    @Order(value = 1)
    public static class TestClass {

        @Order(value = 2)
        public String testField;

        @Order(value = 3)
        public void testMethod() {

        }
    }

    @Test
    void findClassAnnotationField() {
        Integer normalValue = AnnotationUtil.findAnnotationField(TestClass.class, Order.class, Order::value);
        System.out.println(normalValue);

        String nullValue = AnnotationUtil.findAnnotationField(TestClass.class, Qualifier.class, Qualifier::value);
        System.out.println(nullValue);

        String defaultValue = AnnotationUtil.findAnnotationField(TestClass.class, Qualifier.class, Qualifier::value, () -> "default");
        System.out.println(defaultValue);
    }

    @Test
    void findFieldAnnotationField() throws NoSuchFieldException {
        Integer normalValue = AnnotationUtil.findAnnotationField(TestClass.class.getDeclaredField("testField"), Order.class, Order::value);
        System.out.println(normalValue);

        String nullValue = AnnotationUtil.findAnnotationField(TestClass.class.getDeclaredField("testField"), Qualifier.class, Qualifier::value);
        System.out.println(nullValue);

        String defaultValue = AnnotationUtil.findAnnotationField(TestClass.class.getDeclaredField("testField"), Qualifier.class, Qualifier::value, () -> "default");
        System.out.println(defaultValue);
    }

    @Test
    void findMethodAnnotationField() throws NoSuchMethodException {
        Integer normalValue = AnnotationUtil.findAnnotationField(TestClass.class.getDeclaredMethod("testMethod"), Order.class, Order::value);
        System.out.println(normalValue);

        String nullValue = AnnotationUtil.findAnnotationField(TestClass.class.getDeclaredMethod("testMethod"), Qualifier.class, Qualifier::value);
        System.out.println(nullValue);

        String defaultValue = AnnotationUtil.findAnnotationField(TestClass.class.getDeclaredMethod("testMethod"), Qualifier.class, Qualifier::value, () -> "default");
        System.out.println(defaultValue);
    }
}
