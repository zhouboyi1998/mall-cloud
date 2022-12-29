package com.cafe.order.model;

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
 * @Package: com.cafe.order.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 保障实体模型
 */
@ApiModel(value = "Guarantee", description = "保障实体模型")
@TableName("mall_guarantee")
public class Guarantee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "保障ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "保障类型: 0 七天无理由退货, 1 保修, 2 以旧换新")
    private Integer guaranteeType;

    @ApiModelProperty(value = "保障期限")
    private Integer guaranteePeriod;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态: 0 失效, 1 生效")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public Guarantee setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getGuaranteeType() {
        return guaranteeType;
    }

    public Guarantee setGuaranteeType(Integer guaranteeType) {
        this.guaranteeType = guaranteeType;
        return this;
    }

    public Integer getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public Guarantee setGuaranteePeriod(Integer guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Guarantee setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Guarantee setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Guarantee setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Guarantee setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "Guarantee{" +
            "id=" + id +
            ", guaranteeType=" + guaranteeType +
            ", guaranteePeriod=" + guaranteePeriod +
            ", remark=" + remark +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
