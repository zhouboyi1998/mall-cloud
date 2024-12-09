package com.cafe.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cafe.storage.model.dto.CartDTO;
import com.cafe.storage.model.entity.Stock;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.service
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 库存业务接口
 */
public interface StockService extends IService<Stock> {

    /**
     * 批量入库
     *
     * @param cartDTOList
     * @return
     */
    void inboundBatch(List<CartDTO> cartDTOList);

    /**
     * 批量出库
     *
     * @param cartDTOList
     * @return 库存不足的 SKU 主键列表
     */
    List<String> outboundBatch(List<CartDTO> cartDTOList);
}
