package com.cafe.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.goods.model.Spu;
import com.cafe.goods.vo.SpuVO;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.service
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 标准化产品单元业务接口
 */
public interface SpuService extends IService<Spu> {

    /**
     * 根据 skuId 查询 SPU 视图模型
     *
     * @param skuId
     * @return
     */
    SpuVO vo(Long skuId);
}
