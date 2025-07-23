package com.cafe.user.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.model.bo
 * @Author: zhouboyi
 * @Date: 2022/9/48 09:47
 * @Description: [资源内容-角色名称] 关联关系业务模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ResourceRoleBO", description = "[资源内容-角色名称] 关联关系业务模型")
public class ResourceRoleBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源内容")
    private String resourceContent;

    @ApiModelProperty(value = "角色名称列表")
    private List<String> roleNameList;
}
