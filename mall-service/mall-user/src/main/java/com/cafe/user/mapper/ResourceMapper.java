package com.cafe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.dto.MenuTreeDTO;
import com.cafe.user.model.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.mapper
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 资源数据访问接口
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据权限列表查询菜单树DTO列表
     *
     * @param authorities 权限列表
     * @return 菜单树DTO列表
     */
    List<MenuTreeDTO> selectMenuTreeDTOList(@Param("authorities") List<String> authorities);
}
