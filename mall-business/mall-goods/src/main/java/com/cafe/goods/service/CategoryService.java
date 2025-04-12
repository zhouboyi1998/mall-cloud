package com.cafe.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.goods.model.entity.Category;
import com.cafe.goods.model.query.CategoryTreeListQuery;
import com.cafe.goods.model.query.CategoryTreeNodeQuery;
import com.cafe.goods.model.vo.CategoryTreeVO;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 分类业务接口
 */
public interface CategoryService extends IService<Category> {

    /**
     * 根据条件查询分类树节点
     *
     * @param query 查询条件
     * @return 分类树节点
     */
    CategoryTreeVO treeNode(CategoryTreeNodeQuery query);

    /**
     * 根据条件查询分类树列表
     *
     * @param query 查询条件
     * @return 分类树列表
     */
    List<CategoryTreeVO> treeList(CategoryTreeListQuery query);
}
