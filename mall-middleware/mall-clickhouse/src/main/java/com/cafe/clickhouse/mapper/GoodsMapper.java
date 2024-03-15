package com.cafe.clickhouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.clickhouse.model.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.clickhouse.mapper
 * @Author: zhouboyi
 * @Date: 2024/3/15 17:06
 * @Description: 商品数据访问接口
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}
