package com.cafe.starter.boot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.boot.condition
 * @Author: zhouboyi
 * @Date: 2025/7/29 10:56
 * @Description: 服务条件判断器
 */
public class OnServiceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, @NonNull AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 从环境变量中获取服务名称
        Environment environment = conditionContext.getEnvironment();
        String applicationName = environment.getProperty("spring.application.name");
        // 没有找到服务名称, 默认加载
        if (Objects.isNull(applicationName)) {
            return true;
        }

        // 获取注解属性映射
        MultiValueMap<String, Object> attributes = annotatedTypeMetadata.getAllAnnotationAttributes(ConditionalOnService.class.getName());
        if (Objects.nonNull(attributes)) {
            // 获取包含的服务列表
            List<String> includeServices = getServices(attributes, "includeServices");
            // 判断当前服务是否在包含的服务列表中
            if (!CollectionUtils.isEmpty(includeServices)) {
                return includeServices.contains(applicationName);
            }

            // 获取排除的服务列表
            List<String> excludeServices = getServices(attributes, "excludeServices");
            // 判断当前服务是否在排除的服务列表中
            if (!CollectionUtils.isEmpty(excludeServices)) {
                return !excludeServices.contains(applicationName);
            }
        }

        // 默认加载
        return true;
    }

    /**
     * 获取服务名称列表
     *
     * @param attributes    注解属性映射
     * @param attributeName 注解属性名称
     * @return 服务名称列表
     */
    private List<String> getServices(MultiValueMap<String, Object> attributes, String attributeName) {
        // 从注解属性映射中获取注解属性值
        return Optional.ofNullable(attributes.get(attributeName))
            .orElse(Collections.emptyList())
            .stream()
            .filter(Objects::nonNull)
            // 解析注解属性值获取服务名称列表
            .map(this::parseAttribute)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    /**
     * 解析注解属性值获取服务名称列表
     *
     * @param attributeValue 注解属性值
     * @return 服务名称列表
     */
    private List<String> parseAttribute(Object attributeValue) {
        // 解析字符串类型的注解属性值
        if (attributeValue instanceof String) {
            return Collections.singletonList((String) attributeValue);
        }
        // 解析字符串数组类型的注解属性值
        if (attributeValue instanceof String[]) {
            return new ArrayList<>(Arrays.asList((String[]) attributeValue));
        }
        // 默认返回空列表
        return new ArrayList<>();
    }
}
