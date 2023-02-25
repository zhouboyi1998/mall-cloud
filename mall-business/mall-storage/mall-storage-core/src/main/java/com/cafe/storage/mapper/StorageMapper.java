package com.cafe.storage.mapper;

import com.cafe.storage.model.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.storage.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 仓库数据访问接口
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

}
