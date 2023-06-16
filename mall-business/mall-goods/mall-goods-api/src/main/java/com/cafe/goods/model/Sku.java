package com.cafe.goods.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * @Package: com.cafe.goods.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位实体模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Sku", description = "库存量单位实体模型")
@TableName("mall_sku")
public class Sku implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "SKU ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "SKU 名称")
    private String skuName;

    @ApiModelProperty(value = "SPU ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long spuId;

    @ApiModelProperty(value = "SKU 原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "SKU 折扣价")
    private BigDecimal discountPrice;

    @ApiModelProperty(value = "SKU 秒杀价")
    private BigDecimal seckillPrice;

    @ApiModelProperty(value = "SKU 图片")
    private String image;

    @ApiModelProperty(value = "SKU 图片列表")
    private String imageList;

    @ApiModelProperty(value = "SKU 视频")
    private String video;

    @ApiModelProperty(value = "SKU 规格")
    private String specification;

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "库存单位")
    private String unit;

    @ApiModelProperty(value = "销量")
    private Integer saleVolume;

    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "审核: 0 未审核, 1 驳回, 2 通过")
    private Integer audit;

    @ApiModelProperty(value = "状态: 0 下架, 1 上架")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime launchTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;
}
