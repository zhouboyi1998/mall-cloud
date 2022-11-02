package com.cafe.admin.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.bo
 * @Author: zhouboyi
 * @Date: 2022/9/48 09:47
 * @Description:
 */
@ApiModel(value = "MenuRoleBO对象", description = "菜单路径-角色名称关联关系")
public class MenuRoleBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单路径")
    private String menuPath;

    @ApiModelProperty(value = "角色名称列表")
    private ArrayList<String> roleNameList;

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public ArrayList<String> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(ArrayList<String> roleNameList) {
        this.roleNameList = roleNameList;
    }

    @Override
    public String toString() {
        return "MenuRoleBO{" +
            ", menuPath=" + menuPath +
            ", roleNameList=" + roleNameList +
            "}";
    }
}
