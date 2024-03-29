package com.cafe.user.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.user.bo
 * @Author: zhouboyi
 * @Date: 2022/9/48 09:47
 * @Description: 菜单-角色关联关系业务对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "MenuRoleBO", description = "菜单-角色关联关系业务模型")
public class MenuRoleBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单路径")
    private String menuPath;

    @ApiModelProperty(value = "角色名称列表")
    private ArrayList<String> roleNameList;
}
