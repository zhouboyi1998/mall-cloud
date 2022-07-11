package com.cafe.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.admin.model.Menu;
import com.cafe.admin.vo.MenuTreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单 (Mapper 接口)
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取菜单列表树形VO
     *
     * @param roleNameList
     * @return
     */
    List<MenuTreeVO> listMenuTreeVO(@Param("roleNameList") List<String> roleNameList);
}
