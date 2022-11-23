package com.cafe.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.Platform;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.dao
 * @Author: zhouboyi
 * @Date: 2022-11-23
 * @Description: 平台数据访问接口
 */
@Mapper
public interface PlatformMapper extends BaseMapper<Platform> {

}
