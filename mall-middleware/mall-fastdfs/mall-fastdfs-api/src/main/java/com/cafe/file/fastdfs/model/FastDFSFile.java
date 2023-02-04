package com.cafe.file.fastdfs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.model
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:32
 * @Description: FastDFS 文件模型
 */
@ApiModel(value = "FastDFSFile", description = "FastDFS 文件模型")
public class FastDFSFile implements Serializable {

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件扩展名")
    private String extension;

    @ApiModelProperty(value = "文件内容")
    private byte[] content;

    public String getName() {
        return name;
    }

    public FastDFSFile setName(String name) {
        this.name = name;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public FastDFSFile setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public byte[] getContent() {
        return content;
    }

    public FastDFSFile setContent(byte[] content) {
        this.content = content;
        return this;
    }
}
