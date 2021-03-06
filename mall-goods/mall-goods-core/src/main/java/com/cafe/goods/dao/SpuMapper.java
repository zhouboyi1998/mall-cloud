package com.cafe.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.Spu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: Standard Product Unit 标准化产品单元 (Mapper 接口)
 */
@Mapper
public interface SpuMapper extends BaseMapper<Spu> {

}
