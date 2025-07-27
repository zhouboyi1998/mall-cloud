package com.cafe.foundation.validator;

import com.cafe.common.constant.app.ServiceConstant;
import com.cafe.foundation.annotation.Sensitive;
import com.cafe.foundation.feign.SensitiveFeign;
import com.cafe.starter.boot.condition.ConditionalOnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.validator
 * @Author: zhouboyi
 * @Date: 2025/7/29 10:20
 * @Description: 敏感词校验器
 */
@Slf4j
@RequiredArgsConstructor
@Component
@ConditionalOnService(excludeServices = ServiceConstant.MALL_FOUNDATION)
public class SensitiveValidator implements ConstraintValidator<Sensitive, String> {

    private final SensitiveFeign sensitiveFeign;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // 如果字段值为空, 不进行敏感词校验, 直接放行
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        try {
            // 校验字段文本中是否包含敏感词
            return !Optional.ofNullable(sensitiveFeign.validateText(value))
                .map(ResponseEntity::getBody)
                .orElse(Boolean.FALSE);
        } catch (Exception e) {
            // 出现异常时, 打印日志, 放行
            log.error("SensitiveValidator.isValid(): sensitive word validation failed, value -> {}, message -> {}", value, e.getMessage(), e);
            return true;
        }
    }
}
