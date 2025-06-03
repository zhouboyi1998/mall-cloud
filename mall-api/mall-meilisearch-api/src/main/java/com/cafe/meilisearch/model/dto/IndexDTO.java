package com.cafe.meilisearch.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.model.dto
 * @Author: zhouboyi
 * @Date: 2025/6/4 22:46
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "IndexDTO", description = "MeiliSearch 索引数据传输模型")
public class IndexDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "索引UID")
    private String uid;

    @ApiModelProperty(value = "索引主键")
    private String primaryKey;

    @ApiModelProperty(value = "创建时间")
    private String createdAt;

    @ApiModelProperty(value = "更新时间")
    private String updatedAt;
}
