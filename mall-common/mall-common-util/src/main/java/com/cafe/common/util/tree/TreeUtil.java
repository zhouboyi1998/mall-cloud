package com.cafe.common.util.tree;

import com.cafe.common.constant.model.DefaultValueConstant;
import com.cafe.common.lang.tree.Tree;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.tree
 * @Author: zhouboyi
 * @Date: 2024/5/10 15:48
 * @Description: 树形工具类
 */
public class TreeUtil {

    /**
     * 构建树形节点
     *
     * @param nodeList 节点列表
     * @param id       节点ID
     * @param <T>      节点数据类型
     * @return 树形节点
     */
    public static <T extends Tree> Tree buildTreeNode(List<T> nodeList, Long id) {
        return nodeList.stream()
            // 筛选出指定节点
            .filter(node -> Objects.equals(node.getId(), id))
            // 为指定节点组装子节点树
            .map(node -> node.setChildren(buildSubtree(node, nodeList)))
            .findFirst()
            .orElse(new Tree());
    }

    /**
     * 构建树形列表
     *
     * @param nodeList 节点列表
     * @param <T>      节点数据类型
     * @return 树形列表
     */
    public static <T extends Tree> List<Tree> buildTreeList(List<T> nodeList) {
        return nodeList.stream()
            // 筛选出所有一级节点
            .filter(node -> Objects.equals(node.getParentId(), DefaultValueConstant.DEFAULT_PARENT_ID_LONG))
            // 为所有一级节点组装子节点树
            .map(node -> node.setChildren(buildSubtree(node, nodeList)))
            .collect(Collectors.toList());
    }

    /**
     * 构建树形列表
     *
     * @param nodeList 节点列表
     * @param parentId 父节点ID
     * @param <T>      节点数据类型
     * @return 树形列表
     */
    public static <T extends Tree> List<Tree> buildTreeList(List<T> nodeList, Long parentId) {
        return nodeList.stream()
            // 筛选出指定父节点的所有子节点
            .filter(node -> Objects.equals(node.getParentId(), parentId))
            // 为所有筛选出来的节点组装子节点树
            .map(node -> node.setChildren(buildSubtree(node, nodeList)))
            .collect(Collectors.toList());
    }

    /**
     * 为当前节点组装子节点树
     *
     * @param currentNode 当前节点
     * @param nodeList    节点列表
     * @param <T>         树形列表
     * @return 子节点树
     */
    private static <T extends Tree> List<Tree> buildSubtree(T currentNode, List<T> nodeList) {
        return nodeList.stream()
            // 从节点列表中筛选出当前节点的子节点
            .filter(node -> Objects.equals(node.getParentId(), currentNode.getId()))
            // 递归调用组装子节点树
            .map(node -> node.setChildren(buildSubtree(node, nodeList)))
            .collect(Collectors.toList());
    }
}
