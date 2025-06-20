package com.cafe.component.grinder.starter.support.http.apache;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.component.grinder.starter.support.http.apache
 * @Author: zhouboyi
 * @Date: 2025/6/29 20:41
 * @Description: HTTP DELETE method with entity
 */
public class HttpDeleteWithEntity extends HttpEntityEnclosingRequestBase {

    public static final String METHOD_NAME = HttpDelete.METHOD_NAME;

    public HttpDeleteWithEntity() {
        super();
    }

    public HttpDeleteWithEntity(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpDeleteWithEntity(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
}
