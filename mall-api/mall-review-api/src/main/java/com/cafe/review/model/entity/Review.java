package com.cafe.review.model.entity;

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
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.review.model.entity
 * @Author: zhouboyi
 * @Date: 2025/4/28 09:56
 * @Description: 评论实体模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Review", description = "评论实体模型")
@TableName(value = "mall_review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "评分 (1-5分)")
    private Integer rating;

    @ApiModelProperty(value = "文字评论")
    private String comment;

    @ApiModelProperty(value = "审核: 0 未审核, 1 驳回, 2 通过, 3 精选")
    private Integer audit;

    @ApiModelProperty(value = "状态: 0 草稿, 1 发布")
    private Integer status;

    @ApiModelProperty(value = "评论类型: 1 仅评分, 2 有标签, 3 文字评论")
    private Integer reviewType;

    @ApiModelProperty(value = "目标类型: 1 商品评论, 2 订单评论")
    private Integer targetType;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;
}
