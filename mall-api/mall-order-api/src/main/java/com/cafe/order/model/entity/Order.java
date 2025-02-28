package com.cafe.order.model.entity;

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
 * @Package: com.cafe.order.model.entity
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单实体模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Order", description = "订单实体模型")
@TableName(value = "mall_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private Long orderNo;

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "收货地址快照")
    private String address;

    @ApiModelProperty(value = "收货人快照")
    private String receiver;

    @ApiModelProperty(value = "收货人手机号快照")
    private String mobile;

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "折扣/满减金额")
    private BigDecimal discount;

    @ApiModelProperty(value = "优惠券金额")
    private BigDecimal coupon;

    @ApiModelProperty(value = "邮费")
    private BigDecimal postage;

    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal payment;

    @ApiModelProperty(value = "支付渠道: 0 银行卡, 1 支付宝, 2 微信")
    private Integer channel;

    @ApiModelProperty(value = "发票: 0 不开发票, 1 电子发票")
    private Integer invoice;

    @ApiModelProperty(value = "状态: 0 下单, 1 支付中, 2 付款失败, 3 付款成功/待发货, 4 已发货, 5 完成, 6 取消, 7 申请退款, 8 退款成功")
    private Integer status;

    @ApiModelProperty(value = "下单时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime deliverTime;

    @ApiModelProperty(value = "完成时间")
    private LocalDateTime completionTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;
}
