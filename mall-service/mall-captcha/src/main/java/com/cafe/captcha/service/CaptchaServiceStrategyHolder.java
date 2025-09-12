package com.cafe.captcha.service;

import com.cafe.captcha.property.CaptchaProperties;
import com.cafe.captcha.support.CaptchaGenerator;
import com.cafe.starter.boot.strategy.AbstractStrategyHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service
 * @Author: zhouboyi
 * @Date: 2024/3/4 17:26
 * @Description: 验证码业务策略容器
 */
@RequiredArgsConstructor
@Component
public class CaptchaServiceStrategyHolder extends AbstractStrategyHolder<CaptchaGenerator, CaptchaService> {

    /**
     * 验证码配置
     */
    private final CaptchaProperties captchaProperties;

    @Override
    public CaptchaGenerator getStrategyKey(String strategyName) {
        return Optional.ofNullable(strategyName).map(CaptchaGenerator::findByName).orElse(getDefaultStrategyKey());
    }

    @Override
    public CaptchaGenerator getDefaultStrategyKey() {
        return captchaProperties.getDefaultGenerator();
    }
}
