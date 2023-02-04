package com.cafe.order.model;

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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 售后实体模型
 */
@ApiModel(value = "AfterSale", description = "售后实体模型")
@TableName("mall_after_sale")
public class AfterSale implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "售后ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "会员ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberId;

    @ApiModelProperty(value = "订单明细ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long detailId;

    @ApiModelProperty(value = "售后类型: 0 退货, 1 换货, 2 保障")
    private Integer afterSaleType;

    @ApiModelProperty(value = "售后原因")
    private String reason;

    @ApiModelProperty(value = "凭证列表")
    private String proofList;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "退款渠道: 0 银行卡, 1 支付宝, 2 微信")
    private Integer refundChannel;

    @ApiModelProperty(value = "审核: 0 未审核, 1 驳回, 2 通过")
    private Integer audit;

    @ApiModelProperty(value = "状态: 0 申请中, 1 售后中, 2 中止, 3 完成")
    private Integer status;

    @ApiModelProperty(value = "申请时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "售后完成时间")
    private LocalDateTime completionTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public AfterSale setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getMemberId() {
        return memberId;
    }

    public AfterSale setMemberId(Long memberId) {
        this.memberId = memberId;
        return this;
    }

    public Long getDetailId() {
        return detailId;
    }

    public AfterSale setDetailId(Long detailId) {
        this.detailId = detailId;
        return this;
    }

    public Integer getAfterSaleType() {
        return afterSaleType;
    }

    public AfterSale setAfterSaleType(Integer afterSaleType) {
        this.afterSaleType = afterSaleType;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public AfterSale setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getProofList() {
        return proofList;
    }

    public AfterSale setProofList(String proofList) {
        this.proofList = proofList;
        return this;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public AfterSale setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
        return this;
    }

    public Integer getRefundChannel() {
        return refundChannel;
    }

    public AfterSale setRefundChannel(Integer refundChannel) {
        this.refundChannel = refundChannel;
        return this;
    }

    public Integer getAudit() {
        return audit;
    }

    public AfterSale setAudit(Integer audit) {
        this.audit = audit;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AfterSale setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public AfterSale setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public AfterSale setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public AfterSale setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
        return this;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public AfterSale setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public AfterSale setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "AfterSale{" +
            "id=" + id +
            ", memberId=" + memberId +
            ", detailId=" + detailId +
            ", afterSaleType=" + afterSaleType +
            ", reason=" + reason +
            ", proofList=" + proofList +
            ", refundAmount=" + refundAmount +
            ", refundChannel=" + refundChannel +
            ", audit=" + audit +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", auditTime=" + auditTime +
            ", completionTime=" + completionTime +
            ", deleted=" + deleted +
            "}";
    }
}
