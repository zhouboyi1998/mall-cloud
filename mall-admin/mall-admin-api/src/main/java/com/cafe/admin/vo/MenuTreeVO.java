package com.cafe.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.vo
 * @Author: zhouboyi
 * @Date: 2022/7/7 17:25
 * @Description:
 */
@ApiModel(value = "MenuTreeVO对象", description = "二级菜单树视图")
public class MenuTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单显示标题")
    private String menuTitle;

    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单路径")
    private String menuPath;

    @ApiModelProperty(value = "上级菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "子菜单列表")
    private List<MenuTreeVO> children;

    public Long getId() {
        return id;
    }

    public MenuTreeVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public MenuTreeVO setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public MenuTreeVO setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
        return this;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public MenuTreeVO setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public MenuTreeVO setMenuPath(String menuPath) {
        this.menuPath = menuPath;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public MenuTreeVO setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public MenuTreeVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public List<MenuTreeVO> getChildren() {
        return children;
    }

    public MenuTreeVO setChildren(List<MenuTreeVO> children) {
        this.children = children;
        return this;
    }

    @Override
    public String toString() {
        return "MenuTreeVO{" +
            "id=" + id +
            ", menuName='" + menuName + '\'' +
            ", menuTitle='" + menuTitle + '\'' +
            ", menuIcon='" + menuIcon + '\'' +
            ", menuPath='" + menuPath + '\'' +
            ", parentId=" + parentId +
            ", sort=" + sort +
            ", children=" + children +
            '}';
    }
}
