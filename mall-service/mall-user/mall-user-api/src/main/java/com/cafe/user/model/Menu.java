package com.cafe.user.model;

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
 * @Package: com.cafe.user.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 菜单实体模型
 */
@ApiModel(value = "Menu", description = "菜单实体模型")
@TableName("mall_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    @TableId(value = "id", type = IdType.INPUT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "平台ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long platformId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单标题")
    private String menuTitle;

    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单路径")
    private String menuPath;

    @ApiModelProperty(value = "上级菜单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @ApiModelProperty(value = "菜单等级: 1 一级菜单, 2 二级菜单, 3 三级菜单")
    private Integer menuLevel;

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

    public Menu setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public Menu setPlatformId(Long platformId) {
        this.platformId = platformId;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public Menu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public Menu setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
        return this;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public Menu setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public Menu setMenuPath(String menuPath) {
        this.menuPath = menuPath;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Menu setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public Menu setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Menu setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Menu setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Menu setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Menu setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Menu setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + id +
            ", platformId=" + platformId +
            ", menuName=" + menuName +
            ", menuTitle=" + menuTitle +
            ", description=" + menuIcon +
            ", menuPath=" + menuPath +
            ", parentId=" + parentId +
            ", menuLevel=" + menuLevel +
            ", sort=" + sort +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            "}";
    }
}
