package com.cafe.merchant.mapper;

import com.cafe.merchant.model.Merchant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 商家数据访问接口
 */
@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {

}
