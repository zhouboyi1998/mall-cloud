package com.cafe.admin.bo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.bo
 * @Author: zhouboyi
 * @Date: 2022/9/48 09:47
 * @Description:
 */
@ApiModel(value = "MenuRoleBO对象", description = "菜单路径与角色名称对应关系业务对象")
public class MenuRoleBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String menuPath;

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
