package com.cafe.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Date: 2022-11-23
 * @Description: 用户-平台关联关系实体模型
 */
@ApiModel(value = "UserPlatform", description = "用户-平台关联关系实体模型")
@TableName("mall_user_platform")
public class UserPlatform implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户-平台关联关系ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @ApiModelProperty(value = "平台ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long platformId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public UserPlatform setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserPlatform setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public UserPlatform setPlatformId(Long platformId) {
        this.platformId = platformId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserPlatform setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public UserPlatform setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserPlatform{" +
            "id=" + id +
            ", userId=" + userId +
            ", platformId=" + platformId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
