package com.cafe.meilisearch.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.meilisearch.model.dto
 * @Author: zhouboyi
 * @Date: 2025/6/4 23:37
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Page", description = "MeiliSearch 分页模型")
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "结果集")
    private List<T> results;

    @ApiModelProperty(value = "每页显示数量")
    private Integer limit;

    @ApiModelProperty(value = "偏移量")
    private Integer offset;

    @ApiModelProperty(value = "总数")
    private Integer total;
}
