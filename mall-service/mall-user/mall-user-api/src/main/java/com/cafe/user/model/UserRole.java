package com.cafe.user.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 用户-角色关联关系实体模型
 */
@ApiModel(value = "UserRole", description = "用户-角色关联关系实体模型")
@TableName("mall_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户-角色关联关系ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public UserRole setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserRole setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserRole setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public UserRole setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserRole{" +
            "id=" + id +
            ", userId=" + userId +
            ", roleId=" + roleId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
