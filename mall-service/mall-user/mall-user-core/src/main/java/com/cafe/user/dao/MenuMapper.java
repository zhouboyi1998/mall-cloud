package com.cafe.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cafe.user.model.Menu;
import com.cafe.user.vo.MenuTreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.dao
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单数据访问接口
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色列表获取树形格式的菜单列表
     *
     * @param roleNameList
     * @return
     */
    List<MenuTreeVO> listMenuTree(@Param("roleNameList") List<String> roleNameList);
}
