package com.cafe.starter.boot.interceptor.feign;

import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.constant.request.RequestConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.boot.interceptor.feign
 * @Author: zhouboyi
 * @Date: 2023/7/12 16:25
 * @Description: Feign 请求拦截器
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 添加请求头, 标识为 Feign 请求
        template.header(RequestConstant.Header.IS_FEIGN, StringConstant.TRUE);
    }
}
