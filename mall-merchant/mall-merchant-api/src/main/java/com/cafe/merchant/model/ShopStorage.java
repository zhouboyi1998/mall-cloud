package com.cafe.merchant.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.merchant.model
 * @Author: zhouboyi
 * @Date: 2022-12-29
 * @Description: 店铺-仓库关联关系实体模型
 */
@ApiModel(value = "ShopStorage", description = "店铺-仓库关联关系实体模型")
@TableName("mall_shop_storage")
public class ShopStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺-仓库关联关系ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "店铺ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopId;

    @ApiModelProperty(value = "仓库ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long storageId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public ShopStorage setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getShopId() {
        return shopId;
    }

    public ShopStorage setShopId(Long shopId) {
        this.shopId = shopId;
        return this;
    }

    public Long getStorageId() {
        return storageId;
    }

    public ShopStorage setStorageId(Long storageId) {
        this.storageId = storageId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ShopStorage setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public ShopStorage setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "ShopStorage{" +
            "id=" + id +
            ", shopId=" + shopId +
            ", storageId=" + storageId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            "}";
    }
}
