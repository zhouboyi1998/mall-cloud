package com.cafe.goods.model.converter;

import com.cafe.goods.model.dto.CategoryTreeDTO;
import com.cafe.goods.model.entity.Category;
import com.cafe.goods.model.query.CategoryTreeListQuery;
import com.cafe.goods.model.query.CategoryTreeNodeQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.model.converter
 * @Author: zhouboyi
 * @Date: 2024/5/11 14:53
 * @Description: 分类模型转换器
 */
@Mapper(componentModel = "spring")
public interface CategoryConverter {

    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);

    /**
     * 实体模型 -> 数据传输模型
     *
     * @param category 实体模型
     * @return 数据传输模型
     */
    CategoryTreeDTO toDTO(Category category);

    /**
     * 实体模型列表 -> 数据传输模型列表
     *
     * @param categoryList 实体模型列表
     * @return 数据传输模型列表
     */
    default List<CategoryTreeDTO> toDTOList(List<Category> categoryList) {
        if (CollectionUtils.isEmpty(categoryList)) {
            return Collections.emptyList();
        }
        return categoryList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * 树列表查询条件 -> 实体模型
     *
     * @param query 树列表查询条件
     * @return 实体模型
     */
    Category toEntity(CategoryTreeListQuery query);

    /**
     * 树节点查询条件 -> 实体模型
     *
     * @param query 树节点查询条件
     * @return 实体模型
     */
    Category toEntity(CategoryTreeNodeQuery query);
}
