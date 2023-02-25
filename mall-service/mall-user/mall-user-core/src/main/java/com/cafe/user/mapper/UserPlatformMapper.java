package com.cafe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.UserPlatform;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.mapper
 * @Author: zhouboyi
 * @Date: 2022-11-23
 * @Description: 用户-平台关联关系数据访问接口
 */
@Mapper
public interface UserPlatformMapper extends BaseMapper<UserPlatform> {

}
