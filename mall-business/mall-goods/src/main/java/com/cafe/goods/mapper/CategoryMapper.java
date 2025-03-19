package com.cafe.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.dto.CategoryTreeDTO;
import com.cafe.goods.model.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类数据访问接口
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据条件查询分类树DTO列表
     *
     * @param category 查询条件
     * @return 分类树DTO列表
     */
    List<CategoryTreeDTO> selectTreeDTOList(@Param(value = "category") Category category);
}
