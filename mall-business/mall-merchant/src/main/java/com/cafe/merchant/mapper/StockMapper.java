package com.cafe.merchant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.merchant.model.Stock;
import com.cafe.merchant.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 库存数据访问接口
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

    /**
     * 入库
     *
     * @param cartVO
     * @return
     */
    Integer inbound(@Param(value = "cartVO") CartVO cartVO);

    /**
     * 出库
     *
     * @param cartVO
     * @return
     */
    Integer outbound(@Param(value = "cartVO") CartVO cartVO);
}
