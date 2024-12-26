package com.cafe.fastdfs.model.converter;

import com.cafe.fastdfs.model.vo.FileInfoVO;
import org.csource.fastdfs.FileInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.fastdfs.model.converter
 * @Author: zhouboyi
 * @Date: 2024/12/25 17:35
 * @Description: 文件信息转换器
 */
@Mapper(componentModel = "spring")
public interface FileInfoConverter {

    FileInfoConverter INSTANCE = Mappers.getMapper(FileInfoConverter.class);

    /**
     * 文件信息 -> 视图模型
     *
     * @param fileInfo 文件信息
     * @return 视图模型
     */
    @Mappings(value = {
        @Mapping(expression = "java(fileInfo.getSourceIpAddr())", target = "sourceIpAddr"),
        @Mapping(expression = "java(fileInfo.getFileSize())", target = "fileSize"),
        @Mapping(expression = "java(fileInfo.getCreateTimestamp())", target = "createTimestamp"),
    })
    FileInfoVO toVO(FileInfo fileInfo);
}
