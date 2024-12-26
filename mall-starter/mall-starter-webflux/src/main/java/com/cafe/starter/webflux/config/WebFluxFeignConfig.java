package com.cafe.starter.webflux.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.webflux.config
 * @Author: zhouboyi
 * @Date: 2024/12/7 3:00
 * @Description: WebFlux Feign 配置类
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class WebFluxFeignConfig {

    /**
     * ResponseEntity 解码器
     */
    @Bean
    @ConditionalOnMissingBean(value = ResponseEntityDecoder.class)
    public ResponseEntityDecoder responseEntityDecoder() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectFactory<HttpMessageConverters> httpMessageConvertersObjectFactory = () -> new HttpMessageConverters(mappingJackson2HttpMessageConverter);
        SpringDecoder springDecoder = new SpringDecoder(httpMessageConvertersObjectFactory);
        return new ResponseEntityDecoder(springDecoder);
    }
}
