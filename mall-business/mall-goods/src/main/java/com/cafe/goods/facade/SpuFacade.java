package com.cafe.goods.facade;

import com.cafe.goods.vo.SpuVO;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.facade
 * @Author: zhouboyi
 * @Date: 2024/12/3 17:28
 * @Description: 标准化产品单元防腐层接口
 */
public interface SpuFacade {

    /**
     * 根据 skuId 查询 SPU 视图模型
     *
     * @param skuId 库存量单位ID
     * @return 标准化产品单元VO
     */
    SpuVO vo(Long skuId);
}
