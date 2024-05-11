package com.cafe.foundation.converter;

import com.cafe.foundation.model.Area;
import com.cafe.foundation.vo.AreaTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.foundation.converter
 * @Author: zhouboyi
 * @Date: 2024/5/11 10:51
 * @Description:
 */
@Mapper(componentModel = "spring")
public interface AreaConverter {

    AreaConverter INSTANCE = Mappers.getMapper(AreaConverter.class);

    /**
     * 实体模型 -> 树视图模型
     *
     * @param area
     * @return
     */
    AreaTreeVO toTreeVO(Area area);

    /**
     * 实体模型列表 -> 树视图模型列表
     *
     * @param areaList
     * @return
     */
    default List<AreaTreeVO> toTreeVOList(List<Area> areaList) {
        if (CollectionUtils.isEmpty(areaList)) {
            return Collections.emptyList();
        }
        return areaList.stream().map(this::toTreeVO).collect(Collectors.toList());
    }
}
