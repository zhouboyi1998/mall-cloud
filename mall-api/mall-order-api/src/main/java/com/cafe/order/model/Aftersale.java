package com.cafe.order.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Aftersale", description = "售后实体模型")
@TableName(value = "mall_aftersale")
public class Aftersale implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "售后ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "订单明细ID")
    private Long orderItemId;

    @ApiModelProperty(value = "售后类型: 0 退货, 1 换货, 2 保障")
    private Integer aftersaleType;

    @ApiModelProperty(value = "售后原因")
    private String reason;

    @ApiModelProperty(value = "图片列表")
    private String imageList;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "退款渠道: 0 银行卡, 1 支付宝, 2 微信")
    private Integer refundChannel;

    @ApiModelProperty(value = "审核: 0 未审核, 1 驳回, 2 通过")
    private Integer audit;

    @ApiModelProperty(value = "状态: 0 申请中, 1 售后中, 2 中止, 3 完成")
    private Integer status;

    @ApiModelProperty(value = "申请时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "售后完成时间")
    private LocalDateTime completionTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;
}
