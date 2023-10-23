package com.cafe.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.member.model.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.member.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 收货地址数据访问接口
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}
