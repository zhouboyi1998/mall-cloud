package com.cafe.system.model;

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
 * @Package: com.cafe.system.model
 * @Author: zhouboyi
 * @Date: 2023-05-16
 * @Description: 主题实体模型
 */
@ApiModel(value = "Theme", description = "主题实体模型")
@TableName("mall_theme")
public class Theme implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主题id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "基调色名称")
    private String basicName;

    @ApiModelProperty(value = "基调色十六进制代码")
    private String basicCode;

    @ApiModelProperty(value = "辅助色名称")
    private String hoverName;

    @ApiModelProperty(value = "辅助色十六进制代码")
    private String hoverCode;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

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

    public Theme setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBasicName() {
        return basicName;
    }

    public Theme setBasicName(String basicName) {
        this.basicName = basicName;
        return this;
    }

    public String getBasicCode() {
        return basicCode;
    }

    public Theme setBasicCode(String basicCode) {
        this.basicCode = basicCode;
        return this;
    }

    public String getHoverName() {
        return hoverName;
    }

    public Theme setHoverName(String hoverName) {
        this.hoverName = hoverName;
        return this;
    }

    public String getHoverCode() {
        return hoverCode;
    }

    public Theme setHoverCode(String hoverCode) {
        this.hoverCode = hoverCode;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Theme setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Theme setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Theme setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Theme setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Theme setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Theme{" +
            "id=" + id +
            ", basicName=" + basicName +
            ", basicCode=" + basicCode +
            ", hoverName=" + hoverName +
            ", hoverCode=" + hoverCode +
            ", sort=" + sort +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            "}";
    }
}
