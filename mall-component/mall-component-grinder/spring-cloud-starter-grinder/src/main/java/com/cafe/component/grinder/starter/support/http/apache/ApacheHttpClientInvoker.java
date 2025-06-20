package com.cafe.component.grinder.starter.support.http.apache;

import com.cafe.component.grinder.starter.support.http.HttpInvoker;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
 * @Package: com.cafe.component.grinder.starter.support.http.apache
 * @Author: zhouboyi
 * @Date: 2025/6/26 17:13
 * @Description: Apache HttpClient 调用器
 */
public class ApacheHttpClientInvoker implements HttpInvoker {

    @Override
    public void invoke(HttpServletRequest request, HttpServletResponse response, String targetURI) throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            // 根据类型创建 HTTP 请求
            String method = request.getMethod();
            HttpMethod httpMethod = HttpMethod.resolve(method);
            if (Objects.isNull(httpMethod)) {
                throw new IllegalArgumentException("Unsupported request method: " + method);
            }
            HttpRequestBase httpRequestBase = createRequestMethod(httpMethod, targetURI);

            // 复制请求头
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                // 跳过 Content-Length 请求头, Apache HttpClient 会自动管理 Content-Length 请求头的值
                if (HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(headerName)) {
                    continue;
                }
                httpRequestBase.setHeader(headerName, request.getHeader(headerName));
            }

            // 复制请求体
            if (Objects.equals(httpMethod, HttpMethod.POST) ||
                Objects.equals(httpMethod, HttpMethod.PUT) ||
                Objects.equals(httpMethod, HttpMethod.PATCH) ||
                Objects.equals(httpMethod, HttpMethod.DELETE)
            ) {
                InputStream inputStream = request.getInputStream();
                HttpEntity entity = new InputStreamEntity(inputStream);
                ((HttpEntityEnclosingRequestBase) httpRequestBase).setEntity(entity);
            }

            // 执行请求并获取响应
            HttpResponse httpResponse = httpClient.execute(httpRequestBase);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            response.setStatus(statusCode);

            // 复制响应头
            Header[] headers = httpResponse.getAllHeaders();
            for (Header header : headers) {
                response.setHeader(header.getName(), header.getValue());
            }

            // 复制响应体
            InputStream inputStream = httpResponse.getEntity().getContent();
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }

    private HttpRequestBase createRequestMethod(HttpMethod httpMethod, String targetURI) {
        switch (httpMethod) {
            case GET:
                return new HttpGet(targetURI);
            case POST:
                return new HttpPost(targetURI);
            case PUT:
                return new HttpPut(targetURI);
            case DELETE:
                return new HttpDeleteWithEntity(targetURI);
            case PATCH:
                return new HttpPatch(targetURI);
            case HEAD:
                return new HttpHead(targetURI);
            case OPTIONS:
                return new HttpOptions(targetURI);
            default:
                throw new IllegalArgumentException("Unsupported http method: " + httpMethod.name());
        }
    }
}
