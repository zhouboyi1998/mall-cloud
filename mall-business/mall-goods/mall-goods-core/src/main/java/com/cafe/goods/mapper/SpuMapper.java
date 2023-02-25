package com.cafe.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.goods.model.Spu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.goods.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 标准化产品单元数据访问接口
 */
@Mapper
public interface SpuMapper extends BaseMapper<Spu> {

}
