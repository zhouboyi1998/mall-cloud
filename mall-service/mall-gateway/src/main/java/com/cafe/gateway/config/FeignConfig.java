package com.cafe.gateway.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.gateway.config
 * @Author: zhouboyi
 * @Date: 2024/12/7 3:00
 * @Description:
 */
@Configuration
public class FeignConfig {

    /**
     * ResponseEntity 解码器
     *
     * @return
     */
    @Bean
    public ResponseEntityDecoder responseEntityDecoder() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectFactory<HttpMessageConverters> httpMessageConvertersObjectFactory = () -> new HttpMessageConverters(mappingJackson2HttpMessageConverter);
        SpringDecoder springDecoder = new SpringDecoder(httpMessageConvertersObjectFactory);
        return new ResponseEntityDecoder(springDecoder);
    }
}
