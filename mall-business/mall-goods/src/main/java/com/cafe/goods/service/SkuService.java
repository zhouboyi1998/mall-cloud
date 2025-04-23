package com.cafe.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.goods.model.entity.Sku;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位业务接口
 */
public interface SkuService extends IService<Sku> {

    /**
     * 根据ids查询未上架的库存量单位列表
     *
     * @param skuIds 库存量单位ID列表
     * @return 库存量单位列表
     */
    List<Sku> offShelveList(List<Long> skuIds);

    /**
     * 批量修改库存量单位状态
     *
     * @param status 商品状态
     * @param skuIds 库存量单位ID列表
     * @return 影响行数
     */
    Integer updateStatus(Integer status, List<Long> skuIds);
}
