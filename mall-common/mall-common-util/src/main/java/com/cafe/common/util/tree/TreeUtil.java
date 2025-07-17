package com.cafe.common.util.tree;

import com.cafe.common.constant.model.DefaultValueConstant;
import com.cafe.common.lang.datastructures.tree.Tree;
import com.cafe.common.lang.datastructures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
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
     * 递归构建器
     */
    public static class RecursiveBuilder {

        /**
         * 构建树形节点
         *
         * @param nodeList 节点列表
         * @param id       节点ID
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形节点
         */
        public static <T extends Tree<T, K>, K> T buildTreeNode(List<T> nodeList, K id) {
            return RecursiveBuilder.buildTreeNode(nodeList, id, false);
        }

        /**
         * 构建树形节点
         *
         * @param nodeList 节点列表
         * @param id       节点ID
         * @param parallel 是否并行执行
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形节点
         */
        @SuppressWarnings(value = "unchecked")
        public static <T extends Tree<T, K>, K> T buildTreeNode(List<T> nodeList, K id, boolean parallel) {
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                // 筛选出指定节点
                .filter(node -> Objects.equals(node.getId(), id))
                // 为指定节点组装子节点树
                .map(node -> node.setChildren(buildSubtree(node, nodeList, parallel)))
                .findFirst()
                .orElse((T) new TreeNode<K>());
        }

        /**
         * 构建树形列表
         *
         * @param nodeList 节点列表
         * @param clazz    节点ID类型Class对象
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形列表
         */
        public static <T extends Tree<T, K>, K> List<T> buildTreeList(List<T> nodeList, Class<K> clazz) {
            return buildTreeList(nodeList, clazz, false);
        }

        /**
         * 构建树形列表
         *
         * @param nodeList 节点列表
         * @param clazz    节点ID类型Class对象
         * @param parallel 是否并行执行
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形列表
         */
        public static <T extends Tree<T, K>, K> List<T> buildTreeList(List<T> nodeList, Class<K> clazz, boolean parallel) {
            return buildTreeList(nodeList, defaultParentId(clazz), parallel);
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
            return buildTreeList(nodeList, parentId, false);
        }

        /**
         * 构建树形列表
         *
         * @param nodeList 节点列表
         * @param parentId 父节点ID
         * @param parallel 是否并行执行
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形列表
         */
        public static <T extends Tree<T, K>, K> List<T> buildTreeList(List<T> nodeList, K parentId, boolean parallel) {
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                // 筛选出指定父节点的所有子节点
                .filter(node -> Objects.equals(node.getParentId(), parentId))
                // 为所有筛选出来的节点组装子节点树
                .map(node -> node.setChildren(buildSubtree(node, nodeList, parallel)))
                .collect(Collectors.toList());
        }

        /**
         * 为当前节点组装子节点树
         *
         * @param currentNode 当前节点
         * @param nodeList    节点列表
         * @param parallel    是否并行执行
         * @param <T>         节点数据类型
         * @param <K>         节点ID类型
         * @return 子节点树
         */
        private static <T extends Tree<T, K>, K> List<T> buildSubtree(T currentNode, List<T> nodeList, boolean parallel) {
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                // 从节点列表中筛选出当前节点的子节点
                .filter(node -> Objects.equals(node.getParentId(), currentNode.getId()))
                // 递归调用组装子节点树
                .map(node -> node.setChildren(buildSubtree(node, nodeList, parallel)))
                .collect(Collectors.toList());
        }
    }

    /**
     * Map 查询构建器
     */
    public static class MapLookupBuilder {

        /**
         * 构建树形节点
         *
         * @param nodeList 节点列表
         * @param id       节点ID
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形节点
         */
        public static <T extends Tree<T, K>, K> T buildTreeNode(List<T> nodeList, K id) {
            return buildTreeNode(nodeList, id, false);
        }

        /**
         * 构建树形节点
         *
         * @param nodeList 节点列表
         * @param id       节点ID
         * @param parallel 是否并行执行
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形节点
         */
        @SuppressWarnings(value = "unchecked")
        public static <T extends Tree<T, K>, K> T buildTreeNode(List<T> nodeList, K id, boolean parallel) {
            // 初始化节点集合
            Map<K, T> nodeMap = initNodeMap(nodeList, parallel);
            // 遍历节点列表, 构建树形列表
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                // 查询当前节点的父节点, 将当前节点存入父节点的子节点列表中
                .peek(node -> Optional.ofNullable(node.getParentId())
                    .map(nodeMap::get)
                    .map(parent -> parent.getChildren().add(node)))
                // 筛选出指定节点
                .filter(node -> Objects.equals(node.getId(), id))
                // 必须先收集, 再开启一个新的 Stream 流来执行 findAny()
                // 否则在 findAny() 找到第一个符合要求的元素后, 不会再处理其它元素
                .collect(Collectors.toList())
                .stream()
                .findAny()
                .orElse((T) new TreeNode<K>());
        }

        /**
         * 构建树形列表
         *
         * @param nodeList 节点列表
         * @param clazz    节点ID类型Class对象
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形列表
         */
        public static <T extends Tree<T, K>, K> List<T> buildTreeList(List<T> nodeList, Class<K> clazz) {
            return buildTreeList(nodeList, clazz, false);
        }

        /**
         * 构建树形列表
         *
         * @param nodeList 节点列表
         * @param clazz    节点ID类型Class对象
         * @param parallel 是否并行执行
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形列表
         */
        public static <T extends Tree<T, K>, K> List<T> buildTreeList(List<T> nodeList, Class<K> clazz, boolean parallel) {
            return buildTreeList(nodeList, defaultParentId(clazz), parallel);
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
            return buildTreeList(nodeList, parentId, false);
        }

        /**
         * 构建树形列表
         *
         * @param nodeList 节点列表
         * @param parentId 父节点ID
         * @param parallel 是否并行执行
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 树形列表
         */
        public static <T extends Tree<T, K>, K> List<T> buildTreeList(List<T> nodeList, K parentId, boolean parallel) {
            // 初始化节点集合
            Map<K, T> nodeMap = initNodeMap(nodeList, parallel);
            // 遍历节点列表, 构建树形列表
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                // 查询当前节点的父节点, 将当前节点存入父节点的子节点列表中
                .peek(node -> Optional.ofNullable(node.getParentId())
                    .map(nodeMap::get)
                    .map(parent -> parent.getChildren().add(node)))
                // 筛选出指定父节点的所有子节点
                .filter(node -> Objects.equals(node.getParentId(), parentId))
                .collect(Collectors.toList());
        }

        /**
         * 初始化节点集合
         *
         * @param nodeList 节点列表
         * @param parallel 是否并行执行
         * @param <T>      节点数据类型
         * @param <K>      节点ID类型
         * @return 节点集合
         */
        private static <T extends Tree<T, K>, K> Map<K, T> initNodeMap(List<T> nodeList, boolean parallel) {
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                .map(node -> node.setChildren(new ArrayList<>()))
                .collect(Collectors.toMap(Tree::getId, Function.identity()));
        }
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
