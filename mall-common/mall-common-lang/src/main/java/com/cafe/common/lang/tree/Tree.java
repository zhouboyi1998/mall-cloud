package com.cafe.common.lang.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.tree
 * @Author: zhouboyi
 * @Date: 2024/5/9 17:29
 * @Description: 树形结构
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Tree implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long parentId;

    private List<Tree> children;
}
