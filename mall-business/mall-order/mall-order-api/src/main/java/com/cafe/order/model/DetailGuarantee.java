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
 * @Description: 订单明细-保障关联关系实体模型
 */
@ApiModel(value = "DetailGuarantee", description = "订单明细-保障关联关系实体模型")
@TableName("mall_detail_guarantee")
public class DetailGuarantee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单明细-保障关联关系ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "订单明细ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long detailId;

    @ApiModelProperty(value = "保障ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long guaranteeId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expirationTime;

    public Long getId() {
        return id;
    }

    public DetailGuarantee setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDetailId() {
        return detailId;
    }

    public DetailGuarantee setDetailId(Long detailId) {
        this.detailId = detailId;
        return this;
    }

    public Long getGuaranteeId() {
        return guaranteeId;
    }

    public DetailGuarantee setGuaranteeId(Long guaranteeId) {
        this.guaranteeId = guaranteeId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public DetailGuarantee setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public DetailGuarantee setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public DetailGuarantee setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    @Override
    public String toString() {
        return "DetailGuarantee{" +
            "id=" + id +
            ", detailId=" + detailId +
            ", guaranteeId=" + guaranteeId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", expirationTime=" + expirationTime +
            "}";
    }
}
