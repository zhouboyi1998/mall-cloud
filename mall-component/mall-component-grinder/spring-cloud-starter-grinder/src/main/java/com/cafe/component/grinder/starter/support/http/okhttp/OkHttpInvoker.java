package com.cafe.component.grinder.starter.support.http.okhttp;

import com.cafe.component.grinder.starter.support.http.HttpInvoker;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.support.http.okhttp
 * @Author: zhouboyi
 * @Date: 2025/7/8 09:55
 * @Description: OkHttp 调用器
 */
@RequiredArgsConstructor
public class OkHttpInvoker implements HttpInvoker {

    private final OkHttpClient okHttpClient;

    @Override
    public void invoke(HttpServletRequest request, HttpServletResponse response, String targetURI) throws Exception {
        // 获取请求类型
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);
        if (Objects.isNull(httpMethod)) {
            throw new IllegalArgumentException("Unsupported request method: " + method);
        }

        // 使用请求建造器创建 HTTP 请求
        Request.Builder requestBuilder = new Request.Builder().url(targetURI);

        // 复制请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            // 跳过 Content-Length 请求头, OkHttp 会自动管理 Content-Length 请求头的值
            if (HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(headerName)) {
                continue;
            }
            requestBuilder.header(headerName, request.getHeader(headerName));
        }

        // 复制请求体
        RequestBody body = null;
        if (Objects.equals(httpMethod, HttpMethod.POST) ||
            Objects.equals(httpMethod, HttpMethod.PUT) ||
            Objects.equals(httpMethod, HttpMethod.PATCH) ||
            Objects.equals(httpMethod, HttpMethod.DELETE)
        ) {
            InputStream inputStream = request.getInputStream();
            byte[] buffer = new byte[inputStream.available()];
            body = inputStream.read(buffer) > 0 ?
                RequestBody.create(buffer, MediaType.parse(request.getContentType())) :
                RequestBody.create(buffer);
        }
        requestBuilder.method(method, body);

        // 创建 HTTP 请求
        Request okHttpRequest = requestBuilder.build();

        // 执行请求并获取响应
        try (Response okHttpResponse = okHttpClient.newCall(okHttpRequest).execute()) {
            // 设置响应状态码
            response.setStatus(okHttpResponse.code());

            // 复制响应头
            for (String name : okHttpResponse.headers().names()) {
                response.setHeader(name, okHttpResponse.header(name));
            }

            // 复制响应体
            ResponseBody responseBody = okHttpResponse.body();
            if (responseBody != null) {
                try (InputStream inputStream = responseBody.byteStream()) {
                    ServletOutputStream outputStream = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                }
            }
        }
    }
}
