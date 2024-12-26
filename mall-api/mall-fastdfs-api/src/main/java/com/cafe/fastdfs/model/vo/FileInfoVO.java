package com.cafe.fastdfs.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.model.vo
 * @Author: zhouboyi
 * @Date: 2024/12/25 17:29
 * @Description: 文件信息视图模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FileInfoVO", description = "文件信息视图模型")
public class FileInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "来源IP地址")
    private String sourceIpAddr;

    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    @ApiModelProperty(value = "创建时间")
    private Date createTimestamp;

    @ApiModelProperty(value = "文件CRC32校验值")
    private Integer crc32;
}
