package com.cafe.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户-角色关联 (实体类)
 */
@TableName("mall_admin_role_relation")
@ApiModel(value="AdminRoleRelation对象", description="用户-角色关联")
public class AdminRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员-角色关联ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "管理员ID")
    private Long adminId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AdminRoleRelation{" +
        "id=" + id +
        ", adminId=" + adminId +
        ", roleId=" + roleId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
