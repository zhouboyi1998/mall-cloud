package com.cafe.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.manager.model.entity.Manager;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.manager.mapper
 * @Author: zhouboyi
 * @Date: 2024-05-03
 * @Description: 管理员数据访问接口
 */
@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {

}
