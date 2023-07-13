package com.cafe.common.core.feign;

import com.cafe.common.constant.pool.BooleanConstant;
import com.cafe.common.constant.request.RequestConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.feign
 * @Author: zhouboyi
 * @Date: 2023/7/12 16:25
 * @Description: Feign 请求拦截器
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 添加请求头, 标识为 Feign 请求
        template.header(RequestConstant.IS_FEIGN, BooleanConstant.TRUE);
    }
}
