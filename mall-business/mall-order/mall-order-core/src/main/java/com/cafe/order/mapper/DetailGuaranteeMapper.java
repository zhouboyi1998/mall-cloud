package com.cafe.order.mapper;

import com.cafe.order.model.DetailGuarantee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细-保障关联关系数据访问接口
 */
@Mapper
public interface DetailGuaranteeMapper extends BaseMapper<DetailGuarantee> {

}
