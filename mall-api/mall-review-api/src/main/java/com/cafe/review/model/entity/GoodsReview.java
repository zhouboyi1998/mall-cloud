package com.cafe.review.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.model.entity
 * @Author: zhouboyi
 * @Date: 2025/4/25 16:32
 * @Description: 商品-评论关联关系实体模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "GoodsReview", description = "商品-评论关联关系实体模型")
@TableName(value = "mall_goods_review")
public class GoodsReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品-评论关联关系ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "订单明细ID")
    private Long orderItemId;

    @ApiModelProperty(value = "SPU ID")
    private Long spuId;

    @ApiModelProperty(value = "SKU ID")
    private Long skuId;

    @ApiModelProperty(value = "评论ID")
    private Long reviewId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
