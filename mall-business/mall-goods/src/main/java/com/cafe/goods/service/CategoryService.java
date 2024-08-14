package com.cafe.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.common.lang.tree.Tree;
import com.cafe.goods.model.Category;

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
     * @param category
     * @return
     */
    Tree treeNode(Category category);

    /**
     * 根据条件查询分类树列表
     *
     * @param category
     * @return
     */
    List<Tree> treeList(Category category);
}
