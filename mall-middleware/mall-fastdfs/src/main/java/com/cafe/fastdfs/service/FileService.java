package com.cafe.fastdfs.service;

import com.cafe.fastdfs.model.vo.FileInfoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.service
 * @Author: zhouboyi
 * @Date: 2022/7/23 20:03
 * @Description:
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件组 + 文件名 (例如: /group1/M00/00/00/xxx.jpg)
     */
    String upload(MultipartFile file);

    /**
     * 下载文件
     *
     * @param group        文件组 (例如: group1)
     * @param filename     文件名 (例如: M00/00/00/xxx.jpg)
     * @param httpResponse HTTP 响应
     */
    void download(String group, String filename, HttpServletResponse httpResponse);

    /**
     * 删除文件
     *
     * @param group    文件组 (例如: group1)
     * @param filename 文件名 (例如: M00/00/00/xxx.jpg)
     * @return 删除结果 (0 删除失败, 1 删除成功)
     */
    Integer delete(String group, String filename);

    /**
     * 获取文件信息
     *
     * @param group    文件组 (例如: group1)
     * @param filename 文件名 (例如: M00/00/00/xxx.jpg)
     * @return 文件信息
     */
    FileInfoVO info(String group, String filename);
}
