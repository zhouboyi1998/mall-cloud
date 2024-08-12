package com.cafe.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.storage.model.StockItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.mapper
 * @Author: zhouboyi
 * @Date: 2024-05-03
 * @Description: 库存明细数据访问接口
 */
@Mapper
public interface StockItemMapper extends BaseMapper<StockItem> {

}
