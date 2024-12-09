package com.cafe.order.model.query;

import com.cafe.common.lang.date.LocalDateTimePeriod;
import com.cafe.order.model.entity.Order;
import com.cafe.order.model.entity.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.model.query
 * @Author: zhouboyi
 * @Date: 2024/7/23 14:58
 * @Description: 订单查询模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "OrderQuery", description = "订单查询模型")
public class OrderQuery extends Order {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单明细")
    private OrderItem orderItem;

    @ApiModelProperty(value = "创建时间区间")
    private LocalDateTimePeriod createTimePeriod;

    @ApiModelProperty(value = "更新时间区间")
    private LocalDateTimePeriod updateTimePeriod;

    @ApiModelProperty(value = "支付时间区间")
    private LocalDateTimePeriod paymentTimePeriod;

    @ApiModelProperty(value = "发货时间区间")
    private LocalDateTimePeriod deliverTimePeriod;

    @ApiModelProperty(value = "完成时间区间")
    private LocalDateTimePeriod completionTimePeriod;
}
