package com.cafe.admin.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.admin.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 管理员实体模型
 */
@ApiModel(value = "Admin", description = "管理员实体模型")
@TableName("mall_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @ApiModelProperty(value = "管理员昵称")
    private String nickname;

    @ApiModelProperty(value = "管理员头像")
    private String avatar;

    @ApiModelProperty(value = "管理员性别: 0 保密, 1 男, 2 女")
    private Integer gender;

    @ApiModelProperty(value = "状态: 0 禁用, 1 正常")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public Admin setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Admin setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public Admin setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public Admin setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public Admin setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Admin setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Admin setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Admin setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Admin setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Admin{" +
            "id=" + id +
            ", userId=" + userId +
            ", nickname=" + nickname +
            ", avatar=" + avatar +
            ", gender=" + gender +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            "}";
    }
}
