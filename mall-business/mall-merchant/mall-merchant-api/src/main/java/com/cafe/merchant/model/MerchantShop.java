package com.cafe.merchant.model;

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
 * @Package: com.cafe.merchant.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 商家-店铺关联关系实体模型
 */
@ApiModel(value = "MerchantShop", description = "商家-店铺关联关系实体模型")
@TableName("mall_merchant_shop")
public class MerchantShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商家-店铺关联关系ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "商家ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long merchantId;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "职位: 0 店员, 1 店长, 2 店主")
    private Integer job;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public MerchantShop setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public MerchantShop setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public Long getShopId() {
        return shopId;
    }

    public MerchantShop setShopId(Long shopId) {
        this.shopId = shopId;
        return this;
    }

    public Integer getJob() {
        return job;
    }

    public MerchantShop setJob(Integer job) {
        this.job = job;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public MerchantShop setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public MerchantShop setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "MerchantShop{" +
            "id=" + id +
            ", merchantId=" + merchantId +
            ", shopId=" + shopId +
            ", job=" + job +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
