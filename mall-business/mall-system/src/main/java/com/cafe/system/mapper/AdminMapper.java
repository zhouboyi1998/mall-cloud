package com.cafe.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.system.model.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.system.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 管理员数据访问接口
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
