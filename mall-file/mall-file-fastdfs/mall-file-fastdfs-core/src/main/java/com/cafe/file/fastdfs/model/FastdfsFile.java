package com.cafe.file.fastdfs.model;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.file.fastdfs.model
 * @Author: zhouboyi
 * @Date: 2022/7/23 19:32
 * @Description:
 */
public class FastdfsFile implements Serializable {

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件扩展名
     */
    private String extension;

    public FastdfsFile(String name, String extension, byte[] content) {
        this.name = name;
        this.extension = extension;
        this.content = content;
    }

    /**
     * 文件内容
     */
    private byte[] content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
