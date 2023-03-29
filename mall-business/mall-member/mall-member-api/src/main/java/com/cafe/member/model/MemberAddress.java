package com.cafe.member.model;

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
 * @Package: com.cafe.member.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 会员收货地址实体模型
 */
@ApiModel(value = "MemberAddress", description = "会员收货地址实体模型")
@TableName("mall_member_address")
public class MemberAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收货地址ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "会员ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberId;

    @ApiModelProperty(value = "省份ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long provinceId;

    @ApiModelProperty(value = "城市ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cityId;

    @ApiModelProperty(value = "区县ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long districtId;

    @ApiModelProperty(value = "具体地址")
    private String address;

    @ApiModelProperty(value = "收货人")
    private String receiver;

    @ApiModelProperty(value = "收货人手机号")
    private String receiverMobile;

    @ApiModelProperty(value = "默认地址: 0 非默认, 1 默认")
    @TableField(value = "is_defaulted")
    private Boolean defaulted;

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

    public MemberAddress setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getMemberId() {
        return memberId;
    }

    public MemberAddress setMemberId(Long memberId) {
        this.memberId = memberId;
        return this;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public MemberAddress setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public MemberAddress setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public MemberAddress setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public MemberAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public MemberAddress setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public MemberAddress setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
        return this;
    }

    public Boolean getDefaulted() {
        return defaulted;
    }

    public MemberAddress setDefaulted(Boolean defaulted) {
        this.defaulted = defaulted;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public MemberAddress setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public MemberAddress setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public MemberAddress setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public MemberAddress setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public MemberAddress setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "MemberAddress{" +
            "id=" + id +
            ", memberId=" + memberId +
            ", provinceId=" + provinceId +
            ", cityId=" + cityId +
            ", districtId=" + districtId +
            ", address=" + address +
            ", receiver=" + receiver +
            ", receiverMobile=" + receiverMobile +
            ", defaulted=" + defaulted +
            ", sort=" + sort +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", deleted=" + deleted +
            "}";
    }
}
