package com.cafe.openapicenter.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.openapicenter.model.entity
 * @Author: zhouboyi
 * @Date: 2024/4/23 20:11
 * @Description: 开放接口模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class API implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求头
     */
    private Map<String, String> header;

    /**
     * 请求参数
     */
    private Map<String, String> query;

    /**
     * 请求体
     */
    private Map<String, String> body;

    public API(String method, String url, Map<String, String> header, Map<String, String> query, Map<String, String> body) {
        this.method = method;
        this.url = url;
        this.header = header == null ? Collections.emptyMap() : header;
        this.query = query == null ? Collections.emptyMap() : query;
        this.body = body == null ? Collections.emptyMap() : body;
    }
}
