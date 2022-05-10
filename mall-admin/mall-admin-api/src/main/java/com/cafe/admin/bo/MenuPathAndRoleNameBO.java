package com.cafe.admin.bo;

import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.bo
 * @Author: zhouboyi
 * @Date: 2022/5/10 23:33
 * @Description:
 */
public class MenuPathAndRoleNameBO {

    private static final long serialVersionUID = 1L;

    private String path;

    private List<String> roleNameList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(List<String> roleNameList) {
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
