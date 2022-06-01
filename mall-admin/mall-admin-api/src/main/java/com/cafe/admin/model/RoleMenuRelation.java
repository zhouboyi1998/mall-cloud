package com.cafe.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 角色-菜单关联 (实体类)
 */
@TableName("mall_role_menu_relation")
@ApiModel(value="RoleMenuRelation对象", description="角色-菜单关联")
public class RoleMenuRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色-菜单关联ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RoleMenuRelation{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", menuId=" + menuId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
