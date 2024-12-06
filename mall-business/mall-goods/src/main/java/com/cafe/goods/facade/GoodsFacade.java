package com.cafe.goods.facade;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.facade
 * @Author: zhouboyi
 * @Date: 2024/12/3 17:58
 * @Description: 商品防腐层接口
 */
public interface GoodsFacade {

    /**
     * 批量上下架商品
     *
     * @param ids    库存量单位ID列表
     * @param status 状态
     */
    void launch(List<Long> ids, Integer status);
}
