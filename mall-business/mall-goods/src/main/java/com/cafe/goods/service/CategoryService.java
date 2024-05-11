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
     * 查询分类树
     *
     * @return
     */
    List<Tree> treeList();

    /**
     * 根据上级分类id查询分类树
     *
     * @param parentId
     * @return
     */
    List<Tree> treeList(Long parentId);

    /**
     * 根据分类id查询分类树
     *
     * @param id
     * @return
     */
    Tree tree(Long id);
}
