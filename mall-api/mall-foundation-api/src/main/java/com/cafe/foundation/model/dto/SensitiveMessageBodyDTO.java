package com.cafe.foundation.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.model.dto
 * @Author: zhouboyi
 * @Date: 2025/8/11 11:52
 * @Description: 敏感词消息体数据传输模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SensitiveMessageBodyDTO", description = "敏感词消息体数据传输模型")
public class SensitiveMessageBodyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "敏感词消息类型")
    private String type;

    @ApiModelProperty(value = "敏感词消息内容")
    private Object content;
}
