package com.cafe.captcha.service;

import com.cafe.captcha.property.CaptchaProperties;
import com.cafe.captcha.support.CaptchaGenerator;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.starter.boot.model.exception.BusinessException;
import com.cafe.starter.boot.pattern.strategy.AbstractSpringStrategyHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service
 * @Author: zhouboyi
 * @Date: 2024/3/4 17:26
 * @Description: 验证码业务策略容器
 */
@RequiredArgsConstructor
@Component
public class CaptchaServiceStrategyHolder extends AbstractSpringStrategyHolder<CaptchaGenerator, CaptchaService> {

    /**
     * 验证码配置
     */
    private final CaptchaProperties captchaProperties;

    /**
     * 根据策略名称获取策略实现类
     *
     * @param name 策略名称
     * @return 策略实现类
     */
    public CaptchaService get(String name) {
        return Objects.isNull(name) ? get() : get(CaptchaGenerator.get(name));
    }

    /**
     * 获取默认策略实现类
     *
     * @return 默认策略实现类
     */
    public CaptchaService get() {
        return get(defaultKey());
    }

    /**
     * 获取默认策略唯一标识符
     *
     * @return 默认策略唯一标识符
     */
    public CaptchaGenerator defaultKey() {
        return captchaProperties.getDefaultGenerator();
    }

    @Override
    protected RuntimeException strategyNotFoundException() {
        return new BusinessException(HttpStatusEnum.CAPTCHA_GENERATOR_NOT_FOUND);
    }

    @Override
    protected RuntimeException strategyNotEnabledException() {
        return new BusinessException(HttpStatusEnum.CAPTCHA_GENERATOR_NOT_ENABLED);
    }
}
