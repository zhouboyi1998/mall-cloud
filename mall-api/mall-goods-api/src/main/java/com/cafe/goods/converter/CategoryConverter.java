package com.cafe.goods.converter;

import com.cafe.goods.model.Category;
import com.cafe.goods.vo.CategoryTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.converter
 * @Author: zhouboyi
 * @Date: 2024/5/11 14:53
 * @Description:
 */
@Mapper(componentModel = "spring")
public interface CategoryConverter {

    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);

    /**
     * 实体模型 -> 树视图模型
     *
     * @param category
     * @return
     */
    CategoryTreeVO toTreeVO(Category category);

    /**
     * 实体模型列表 -> 树视图模型列表
     *
     * @param categoryList
     * @return
     */
    default List<CategoryTreeVO> toTreeVOList(List<Category> categoryList) {
        if (CollectionUtils.isEmpty(categoryList)) {
            return Collections.emptyList();
        }
        return categoryList.stream().map(this::toTreeVO).collect(Collectors.toList());
    }
}
