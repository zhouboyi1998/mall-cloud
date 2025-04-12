package com.cafe.user.model.converter;

import com.cafe.user.model.entity.Resource;
import com.cafe.user.model.vo.ResourceTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.model.converter
 * @Author: zhouboyi
 * @Date: 2025/4/12 19:55
 * @Description: 资源模型转换器
 */
@Mapper(componentModel = "spring")
public interface ResourceConverter {

    ResourceConverter INSTANCE = Mappers.getMapper(ResourceConverter.class);

    /**
     * 实体模型 -> 视图模型
     *
     * @param resource 实体模型
     * @return 树形视图模型
     */
    ResourceTreeVO toTreeVO(Resource resource);

    /**
     * 实体模型列表 -> 树形视图模型列表
     *
     * @param resourceList 实体模型列表
     * @return 树形视图模型列表
     */
    default List<ResourceTreeVO> toTreeVOList(List<Resource> resourceList) {
        if (CollectionUtils.isEmpty(resourceList)) {
            return Collections.emptyList();
        }
        return resourceList.stream().map(this::toTreeVO).collect(Collectors.toList());
    }
}
