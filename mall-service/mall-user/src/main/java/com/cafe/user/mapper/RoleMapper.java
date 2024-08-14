package com.cafe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色数据访问接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询角色名称列表
     *
     * @param userId 用户id
     * @return
     */
    List<String> nameList(@Param(value = "userId") Long userId);
}
