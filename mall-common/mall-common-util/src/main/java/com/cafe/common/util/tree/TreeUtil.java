package com.cafe.common.util.tree;

import com.cafe.common.constant.model.DefaultValueConstant;
import com.cafe.common.lang.tree.Tree;
import com.cafe.common.lang.tree.TreeNode;

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
     * @param <K>      节点ID类型
     * @return 树形节点
     */
    @SuppressWarnings(value = "unchecked")
    public static <T extends Tree<T, K>, K> T buildTreeNode(List<T> nodeList, K id) {
        return nodeList.stream()
            // 筛选出指定节点
            .filter(node -> Objects.equals(node.getId(), id))
            // 为指定节点组装子节点树
            .map(node -> node.setChildren(buildSubtree(node, nodeList)))
            .findFirst()
            .orElse((T) new TreeNode<K>());
    }

    /**
     * 构建树形列表
     *
     * @param nodeList 节点列表
     * @param <T>      节点数据类型
     * @param <K>      节点ID类型
     * @return 树形列表
     */
    public static <T extends Tree<T, K>, K> List<T> buildTreeList(List<T> nodeList, Class<K> clazz) {
        return buildTreeList(nodeList, defaultParentId(clazz));
    }

    /**
     * 构建树形列表
     *
     * @param nodeList 节点列表
     * @param parentId 父节点ID
     * @param <T>      节点数据类型
     * @param <K>      节点ID类型
     * @return 树形列表
     */
    public static <T extends Tree<T, K>, K> List<T> buildTreeList(List<T> nodeList, K parentId) {
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
     * @param <T>         节点数据类型
     * @param <K>         节点ID类型
     * @return 子节点树
     */
    private static <T extends Tree<T, K>, K> List<T> buildSubtree(T currentNode, List<T> nodeList) {
        return nodeList.stream()
            // 从节点列表中筛选出当前节点的子节点
            .filter(node -> Objects.equals(node.getParentId(), currentNode.getId()))
            // 递归调用组装子节点树
            .map(node -> node.setChildren(buildSubtree(node, nodeList)))
            .collect(Collectors.toList());
    }

    /**
     * 获取默认的父节点ID
     *
     * @param clazz 父节点ID类型Class对象
     * @param <K>   父节点ID类型
     * @return 默认的父节点ID
     */
    private static <K> K defaultParentId(Class<K> clazz) {
        if (Objects.equals(clazz, Integer.class)) {
            return clazz.cast(DefaultValueConstant.DEFAULT_PARENT_ID_INTEGER);
        } else if (Objects.equals(clazz, Long.class)) {
            return clazz.cast(DefaultValueConstant.DEFAULT_PARENT_ID_LONG);
        } else if (Objects.equals(clazz, String.class)) {
            return clazz.cast(DefaultValueConstant.DEFAULT_PARENT_ID_STRING);
        } else {
            throw new RuntimeException("Unsupported parent id type!");
        }
    }
}
