package com.cafe.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.entity.Sku;
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
     * @param skuIds 库存量单位ID列表
     * @return 库存量单位列表
     */
    List<Sku> offShelveList(@Param(value = "skuIds") List<Long> skuIds);

    /**
     * 批量修改库存量单位状态
     *
     * @param status 商品状态
     * @param skuIds 库存量单位ID列表
     * @return 影响行数
     */
    Integer updateStatus(@Param(value = "status") Integer status, @Param(value = "skuIds") List<Long> skuIds);
}
