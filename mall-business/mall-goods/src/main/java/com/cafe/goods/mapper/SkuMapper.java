package com.cafe.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位数据访问接口
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 根据ids查询未上架的库存量单位列表
     *
     * @param skuIds
     * @return
     */
    List<Sku> unlisted(@Param(value = "skuIds") List<Long> skuIds);
}
