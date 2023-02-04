package com.cafe.goods.model;

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
 * @Package: com.cafe.goods.model
 * @Author: zhouboyi
 * @Date: 2022-05-09
 * @Description: 库存量单位实体模型
 */
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
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "上架时间")
    private LocalDateTime launchTime;

    @ApiModelProperty(value = "逻辑删除: 0 未删除, 1 已删除")
    @TableField(value = "is_deleted")
    @TableLogic
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public Sku setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSkuName() {
        return skuName;
    }

    public Sku setSkuName(String skuName) {
        this.skuName = skuName;
        return this;
    }

    public Long getSpuId() {
        return spuId;
    }

    public Sku setSpuId(Long spuId) {
        this.spuId = spuId;
        return this;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public Sku setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public Sku setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
        return this;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public Sku setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Sku setImage(String image) {
        this.image = image;
        return this;
    }

    public String getImageList() {
        return imageList;
    }

    public Sku setImageList(String imageList) {
        this.imageList = imageList;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public Sku setVideo(String video) {
        this.video = video;
        return this;
    }

    public String getSpecification() {
        return specification;
    }

    public Sku setSpecification(String specification) {
        this.specification = specification;
        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public Sku setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Sku setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Integer getSaleVolume() {
        return saleVolume;
    }

    public Sku setSaleVolume(Integer saleVolume) {
        this.saleVolume = saleVolume;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public Sku setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getAudit() {
        return audit;
    }

    public Sku setAudit(Integer audit) {
        this.audit = audit;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Sku setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Sku setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Sku setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public Sku setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
        return this;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public Sku setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Sku setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Sku{" +
            "id=" + id +
            ", skuName=" + skuName +
            ", spuId=" + spuId +
            ", originalPrice=" + originalPrice +
            ", discountPrice=" + discountPrice +
            ", seckillPrice=" + seckillPrice +
            ", image=" + image +
            ", imageList=" + imageList +
            ", video=" + video +
            ", specification=" + specification +
            ", stock=" + stock +
            ", unit=" + unit +
            ", saleVolume=" + saleVolume +
            ", sort=" + sort +
            ", audit=" + audit +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", auditTime=" + auditTime +
            ", launchTime=" + launchTime +
            ", deleted=" + deleted +
            "}";
    }
}
