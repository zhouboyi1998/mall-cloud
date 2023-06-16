package com.cafe.order.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.order.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 订单明细-保障关联关系实体模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expirationTime;
}
