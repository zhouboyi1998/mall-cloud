package com.cafe.captcha.service;

import com.cafe.captcha.property.CaptchaProperties;
import com.cafe.common.enumeration.http.HttpStatusEnum;
import com.cafe.common.util.base.StringUtil;
import com.cafe.starter.boot.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.captcha.service
 * @Author: zhouboyi
 * @Date: 2024/3/4 17:26
 * @Description: 验证码业务策略中心
 */
@RequiredArgsConstructor
@Component
public class CaptchaServiceStrategy {

    /**
     * 验证码业务实现类通用后缀
     */
    private static final String CAPTCHA_SERVICE_IMPL_SUFFIX = "CaptchaServiceImpl";

    /**
     * 验证码配置
     */
    private final CaptchaProperties captchaProperties;

    /**
     * 验证码业务实现类列表
     */
    private final List<CaptchaService> captchaServiceList;

    /**
     * 验证码业务实现类映射
     */
    private Map<String, CaptchaService> captchaServiceMap;

    @PostConstruct
    public void initCaptchaServiceMap() {
        // 组装验证码业务实现类映射
        captchaServiceMap = captchaServiceList.stream()
            .collect(Collectors.toMap(captchaService -> StringUtil.lowerCaseFirstLetter(captchaService.getClass().getSimpleName()), Function.identity()));
    }

    /**
     * 通过名称前缀获取验证码业务实现类
     *
     * @param implPrefix 业务实现类名称前缀
     * @return 业务实现类
     */
    public CaptchaService captchaService(String implPrefix) {
        String serviceImplName = (StringUtils.hasText(implPrefix) ? implPrefix : captchaProperties.getService().getDefaultImplPrefix()) + CAPTCHA_SERVICE_IMPL_SUFFIX;
        if (!captchaServiceMap.containsKey(serviceImplName)) {
            throw new BusinessException(HttpStatusEnum.CAPTCHA_SERVICE_IMPL_NOT_FOUND);
        }
        return captchaServiceMap.get(serviceImplName);
    }
}
