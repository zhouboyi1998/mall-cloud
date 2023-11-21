package com.cafe.common.log.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.log.model
 * @Author: zhouboyi
 * @Date: 2023/11/21 10:09
 * @Description: 日志模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 来源 IP
     */
    private String source;

    /**
     * 请求 URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String type;

    /**
     * 控制器类
     */
    private String clazz;

    /**
     * 执行方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String argument;

    /**
     * 相应结果
     */
    private Object result;

    /**
     * 执行耗时
     */
    private Long duration;
}
