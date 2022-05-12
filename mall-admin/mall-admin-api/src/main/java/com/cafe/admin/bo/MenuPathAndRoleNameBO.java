package com.cafe.admin.bo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.bo
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:33
 * @Description:
 */
public class MenuPathAndRoleNameBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String path;

    private ArrayList<String> roleNameList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<String> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(ArrayList<String> roleNameList) {
        this.roleNameList = roleNameList;
    }

    @Override
    public String toString() {
        return "MenuPathAndRoleNameBO{" +
            ", path=" + path +
            ", roleNameList=" + roleNameList +
            "}";
    }
}
