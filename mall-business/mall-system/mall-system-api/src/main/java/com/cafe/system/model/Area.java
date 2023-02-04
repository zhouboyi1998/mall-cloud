package com.cafe.system.model;

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
 * @Date: 2022-12-29
 * @Description: 地址实体模型
 */
@ApiModel(value = "Area", description = "地址实体模型")
@TableName("mall_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "地址名称")
    private String areaName;

    @ApiModelProperty(value = "上级地址ID")
    private Integer parentId;

    @ApiModelProperty(value = "地址等级: 1 省份, 2 城市, 3 区县")
    private Integer areaLevel;

    @ApiModelProperty(value = "邮编")
    private String postcode;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "状态: 0 禁用, 1 正常")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public Area setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public Area setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Area setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public Area setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public Area setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Area setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Area setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Area setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Area setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Area setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Area{" +
            "id=" + id +
            ", areaName=" + areaName +
            ", parentId=" + parentId +
            ", areaLevel=" + areaLevel +
            ", postcode=" + postcode +
            ", sort=" + sort +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            "}";
    }
}
