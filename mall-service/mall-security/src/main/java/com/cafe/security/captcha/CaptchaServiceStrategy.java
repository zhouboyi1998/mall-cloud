package com.cafe.security.captcha;

import com.cafe.common.util.base.StringUtil;
import com.cafe.security.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.security.captcha
 * @Author: zhouboyi
 * @Date: 2024/3/4 17:26
 * @Description: 验证码业务策略中心
 */
@RequiredArgsConstructor
@Component
public class CaptchaServiceStrategy {

    private final List<CaptchaService> captchaServiceList;

    private Map<String, CaptchaService> captchaServiceMap;

    @PostConstruct
    public void initCaptchaServiceMap() {
        captchaServiceMap = captchaServiceList.stream()
            .collect(Collectors.toMap(captchaService -> StringUtil.lowerCaseFirstLetter(captchaService.getClass().getSimpleName()), Function.identity()));
    }

    public CaptchaService captchaService(String name) {
        return captchaServiceMap.get(name);
    }
}
