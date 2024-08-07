package com.cafe.elasticsearch.index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.elasticsearch.index
 * @Author: zhouboyi
 * @Date: 2024/7/31 10:19
 * @Description: 订单全文索引
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OrderIndex", description = "订单全文索引")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OrderIndex {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "订单编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderNo;

    @ApiModelProperty(value = "收货地址快照")
    private String address;

    @ApiModelProperty(value = "收货人快照")
    private String receiver;

    @ApiModelProperty(value = "收货人手机号快照")
    private String mobile;
}
