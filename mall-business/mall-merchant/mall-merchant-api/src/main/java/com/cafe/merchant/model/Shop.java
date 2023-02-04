package com.cafe.merchant.model;

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
 * @Package: com.cafe.merchant.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 店铺实体模型
 */
@ApiModel(value = "Shop", description = "店铺实体模型")
@TableName("mall_shop")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "店铺类型: 0 自营店铺, 1 第三方店铺")
    private Integer shopType;

    @ApiModelProperty(value = "店铺Logo")
    private String logo;

    @ApiModelProperty(value = "店铺简介")
    private String intro;

    @ApiModelProperty(value = "店铺客服电话")
    private String telephone;

    @ApiModelProperty(value = "营业执照")
    private String businessLicense;

    @ApiModelProperty(value = "审核: 0 未审核, 1 驳回, 2 通过")
    private Integer audit;

    @ApiModelProperty(value = "状态: 0 停业, 1 营业")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public Shop setId(Long id) {
        this.id = id;
        return this;
    }

    public String getShopName() {
        return shopName;
    }

    public Shop setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public Integer getShopType() {
        return shopType;
    }

    public Shop setShopType(Integer shopType) {
        this.shopType = shopType;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public Shop setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getIntro() {
        return intro;
    }

    public Shop setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Shop setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public Shop setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
        return this;
    }

    public Integer getAudit() {
        return audit;
    }

    public Shop setAudit(Integer audit) {
        this.audit = audit;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Shop setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Shop setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Shop setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public Shop setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Shop setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Shop{" +
            "id=" + id +
            ", shopName=" + shopName +
            ", shopType=" + shopType +
            ", logo=" + logo +
            ", intro=" + intro +
            ", telephone=" + telephone +
            ", businessLicense=" + businessLicense +
            ", audit=" + audit +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", auditTime=" + auditTime +
            ", deleted=" + deleted +
            "}";
    }
}
