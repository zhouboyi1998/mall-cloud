package com.cafe.merchant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.merchant.model.Stock;
import com.cafe.merchant.vo.CartVO;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.service
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 库存业务接口
 */
public interface StockService extends IService<Stock> {

    /**
     * 批量入库
     *
     * @param cartVOList
     * @return
     */
    void inboundBatch(List<CartVO> cartVOList);

    /**
     * 批量出库
     *
     * @param cartVOList
     * @return 库存不足的 SKU 主键列表
     */
    List<String> outboundBatch(List<CartVO> cartVOList);
}
