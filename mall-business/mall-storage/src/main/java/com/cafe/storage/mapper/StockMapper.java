package com.cafe.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.storage.model.dto.CartDTO;
import com.cafe.storage.model.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 库存数据访问接口
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

    /**
     * 入库
     *
     * @param cartDTO
     * @return
     */
    Integer inbound(@Param(value = "cartDTO") CartDTO cartDTO);

    /**
     * 出库
     *
     * @param cartDTO
     * @return
     */
    Integer outbound(@Param(value = "cartDTO") CartDTO cartDTO);
}
