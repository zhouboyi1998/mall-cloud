package com.cafe.starter.web.controlleradvice.request;

import com.cafe.common.constant.request.RequestConstant;
import com.cafe.common.json.util.JacksonUtil;
import com.cafe.starter.boot.model.Payload;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.web.controlleradvice.request
 * @Author: zhouboyi
 * @Date: 2023/8/7 15:38
 * @Description: 请求头处理器
 */
@RestControllerAdvice(annotations = {RestController.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Order(value = 50)
public class RequestHeaderHandler {

    @ModelAttribute(value = RequestConstant.ModelAttribute.PAYLOAD)
    public Payload payload(HttpServletRequest request) {
        // 获取请求头中的 JWT 载荷
        String payload = request.getHeader(RequestConstant.Header.PAYLOAD);
        // 将 JWT 载荷反序列化成 Payload 类型, 添加到 ModelAttribute 中
        return JacksonUtil.readValue(payload, Payload.class);
    }
}
