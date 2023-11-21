package com.cafe.file.fastdfs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.model
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:32
 * @Description: FastDFS 文件模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FastDFSFile", description = "FastDFS 文件模型")
public class FastDFSFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件扩展名")
    private String extension;

    @ApiModelProperty(value = "文件内容")
    private byte[] content;
}
