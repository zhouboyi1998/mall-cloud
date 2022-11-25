package com.cafe.user.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.bo
 * @Author: zhouboyi
 * @Date: 2022/9/48 09:47
 * @Description: 菜单-角色关联关系业务对象
 */
@ApiModel(value = "MenuRoleBO", description = "菜单-角色关联关系业务对象")
public class MenuRoleBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单路径")
    private String menuPath;

    @ApiModelProperty(value = "角色名称列表")
    private ArrayList<String> roleNameList;

    public String getMenuPath() {
        return menuPath;
    }

    public MenuRoleBO setMenuPath(String menuPath) {
        this.menuPath = menuPath;
        return this;
    }

    public ArrayList<String> getRoleNameList() {
        return roleNameList;
    }

    public MenuRoleBO setRoleNameList(ArrayList<String> roleNameList) {
        this.roleNameList = roleNameList;
        return this;
    }

    @Override
    public String toString() {
        return "MenuRoleBO{" +
            ", menuPath=" + menuPath +
            ", roleNameList=" + roleNameList +
            "}";
    }
}
