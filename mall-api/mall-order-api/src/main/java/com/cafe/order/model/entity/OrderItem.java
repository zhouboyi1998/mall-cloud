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
 * @Description: 订单明细实体模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OrderItem", description = "订单明细实体模型")
@TableName(value = "mall_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单明细ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "SKU ID")
    private Long skuId;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "仓库ID")
    private Long storageId;

    @ApiModelProperty(value = "SKU 名称快照")
    private String skuName;

    @ApiModelProperty(value = "SKU 主图快照")
    private String skuImage;

    @ApiModelProperty(value = "SKU 价格快照")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "SKU 购买数量")
    private Integer skuQuantity;

    @ApiModelProperty(value = "订单明细的实际支付金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "评价: 0 未评价, 1 已评价")
    private Integer review;

    @ApiModelProperty(value = "状态: 0 下单, 1 支付中, 2 付款失败, 3 付款成功/待发货, 4 已发货, 5 完成, 6 取消, 7 申请退款, 8 退款成功")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
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
