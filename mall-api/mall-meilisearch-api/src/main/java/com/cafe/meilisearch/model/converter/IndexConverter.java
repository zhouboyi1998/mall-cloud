package com.cafe.meilisearch.model.converter;

import com.cafe.meilisearch.model.dto.IndexDTO;
import com.meilisearch.sdk.Index;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.model.converter
 * @Author: zhouboyi
 * @Date: 2025/6/4 22:53
 * @Description: 索引模型转换器
 */
@Mapper(componentModel = "spring")
public interface IndexConverter {

    IndexConverter INSTANCE = Mappers.getMapper(IndexConverter.class);

    /**
     * 实体模型 -> 数据传输模型
     *
     * @param index 实体模型
     * @return 数据传输模型
     */
    IndexDTO toDTO(Index index);

    /**
     * 实体模型列表 -> 数据传输模型列表
     *
     * @param indexList 实体模型列表
     * @return 数据传输模型列表
     */
    default List<IndexDTO> toDTOList(List<Index> indexList) {
        if (CollectionUtils.isEmpty(indexList)) {
            return Collections.emptyList();
        }
        return indexList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
