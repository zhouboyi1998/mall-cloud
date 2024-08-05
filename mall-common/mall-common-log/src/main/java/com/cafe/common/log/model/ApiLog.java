package com.cafe.common.log.model;

import com.cafe.common.constant.date.DateTimeConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.log.model
 * @Author: zhouboyi
 * @Date: 2023/11/21 10:09
 * @Description: 接口日志
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApiLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求时间
     */
    @JsonFormat(pattern = DateTimeConstant.DATE_TIME_WITH_MILLISECOND, shape = JsonFormat.Shape.STRING)
    private LocalDateTime time;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 来源IP
     */
    private String source;

    /**
     * 请求URL
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
    private Map<String, Object> argument;

    /**
     * 响应结果
     */
    private Object result;

    /**
     * 异常信息
     */
    private Throwable throwable;
}
