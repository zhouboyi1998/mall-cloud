package com.cafe.merchant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.merchant.model.Storage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 仓库数据访问接口
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

}
