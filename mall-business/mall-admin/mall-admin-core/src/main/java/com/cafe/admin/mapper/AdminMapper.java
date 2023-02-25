package com.cafe.admin.mapper;

import com.cafe.admin.model.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.mapper
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 管理员数据访问接口
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
