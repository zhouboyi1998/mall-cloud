package com.cafe.foundation.model.converter;

import com.cafe.foundation.model.dto.AreaTreeDTO;
import com.cafe.foundation.model.entity.Area;
import com.cafe.foundation.model.query.AreaTreeListQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.model.converter
 * @Author: zhouboyi
 * @Date: 2024/5/11 10:51
 * @Description: 区域模型转换器
 */
@Mapper(componentModel = "spring")
public interface AreaConverter {

    AreaConverter INSTANCE = Mappers.getMapper(AreaConverter.class);

    /**
     * 实体模型 -> 树数据传输模型
     *
     * @param area 实体模型
     * @return 树数据传输模型
     */
    AreaTreeDTO toTreeDTO(Area area);

    /**
     * 实体模型列表 -> 树数据传输模型列表
     *
     * @param areaList 实体模型列表
     * @return 树数据传输模型列表
     */
    default List<AreaTreeDTO> toTreeDTOList(List<Area> areaList) {
        if (CollectionUtils.isEmpty(areaList)) {
            return Collections.emptyList();
        }
        return areaList.stream().map(this::toTreeDTO).collect(Collectors.toList());
    }

    /**
     * 树列表查询参数 -> 实体模型
     *
     * @param query 树列表查询参数
     * @return 实体模型
     */
    Area toEntity(AreaTreeListQuery query);
}
