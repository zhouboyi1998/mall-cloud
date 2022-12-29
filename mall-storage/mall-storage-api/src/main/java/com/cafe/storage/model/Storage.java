package com.cafe.storage.model;

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
 * @Package: com.cafe.storage.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 仓库实体模型
 */
@ApiModel(value = "Storage", description = "仓库实体模型")
@TableName("mall_storage")
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "仓库名称")
    private String storageName;

    @ApiModelProperty(value = "省份ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long provinceId;

    @ApiModelProperty(value = "城市ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cityId;

    @ApiModelProperty(value = "区县ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long districtId;

    @ApiModelProperty(value = "仓库具体地址")
    private String address;

    @ApiModelProperty(value = "仓库类型: 1 平台仓库, 2 商家仓库")
    private Integer storageType;

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

    public Storage setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStorageName() {
        return storageName;
    }

    public Storage setStorageName(String storageName) {
        this.storageName = storageName;
        return this;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public Storage setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public Storage setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public Storage setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Storage setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getStorageType() {
        return storageType;
    }

    public Storage setStorageType(Integer storageType) {
        this.storageType = storageType;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Storage setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Storage setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Storage setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Storage setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Storage setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Storage{" +
            "id=" + id +
            ", storageName=" + storageName +
            ", provinceId=" + provinceId +
            ", cityId=" + cityId +
            ", districtId=" + districtId +
            ", address=" + address +
            ", storageType=" + storageType +
            ", sort=" + sort +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            "}";
    }
}
