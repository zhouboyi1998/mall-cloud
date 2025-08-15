package com.cafe.common.util.tree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.tree
 * @Author: zhouboyi
 * @Date: 2025/8/15 17:15
 * @Description: 树形构造器
 */
@AllArgsConstructor
public class TreeBuilder<T, K> {

    /**
     * 节点列表
     */
    private final List<T> nodeList;

    /**
     * 获取节点ID的方法
     */
    private final Function<T, K> idGetter;

    /**
     * 获取节点父ID的方法
     */
    private final Function<T, K> parentIdGetter;

    /**
     * 设置节点子节点的方法
     */
    private final BiFunction<T, List<T>, T> childrenSetter;

    /**
     * 筛选根节点的过滤条件
     */
    private final Predicate<T> rootNodeFilter;

    /**
     * 获取默认根节点的方法
     */
    private final Supplier<T> defaultRootSupplier;

    /**
     * 筛选一级节点的过滤条件
     */
    private final Predicate<T> firstLevelNodeFilter;

    /**
     * 是否使用并行流
     */
    private final boolean parallel;

    public static <T, K> Builder<T, K> builder() {
        return new Builder<>();
    }

    /**
     * 构建树形节点
     *
     * @return 树形节点
     */
    public T buildTreeNode() {
        return (parallel ? nodeList.parallelStream() : nodeList.stream())
            .filter(rootNodeFilter)
            .map(node -> childrenSetter.apply(node, buildSubtree(node)))
            .findFirst()
            .orElse(defaultRootSupplier.get());
    }

    /**
     * 构建树形列表
     *
     * @return 树形列表
     */
    public List<T> buildTreeList() {
        return (parallel ? nodeList.parallelStream() : nodeList.stream())
            .filter(firstLevelNodeFilter)
            .map(node -> childrenSetter.apply(node, buildSubtree(node)))
            .collect(Collectors.toList());
    }

    /**
     * 为当前节点组装子节点树
     *
     * @param currentNode 当前节点
     * @return 子节点树
     */
    private List<T> buildSubtree(T currentNode) {
        return (parallel ? nodeList.parallelStream() : nodeList.stream())
            .filter(node -> Objects.equals(parentIdGetter.apply(node), idGetter.apply(currentNode)))
            .map(node -> childrenSetter.apply(node, buildSubtree(node)))
            .collect(Collectors.toList());
    }

    @NoArgsConstructor
    public static class Builder<T, K> {

        /**
         * 节点列表
         */
        private List<T> nodeList;

        /**
         * 获取节点ID的方法
         */
        private Function<T, K> idGetter;

        /**
         * 获取节点父ID的方法
         */
        private Function<T, K> parentIdGetter;

        /**
         * 设置节点子节点的方法
         */
        private BiFunction<T, List<T>, T> childrenSetter;

        /**
         * 筛选根节点的过滤条件
         */
        private Predicate<T> rootNodeFilter;

        /**
         * 获取默认根节点的方法
         */
        private Supplier<T> defaultRootSupplier;

        /**
         * 筛选一级节点的过滤条件
         */
        private Predicate<T> firstLevelNodeFilter;

        /**
         * 是否使用并行流的判断条件
         */
        private BooleanSupplier parallelChecker = () -> false;

        public Builder<T, K> nodeList(List<T> nodeList) {
            this.nodeList = nodeList;
            return this;
        }

        public Builder<T, K> idGetter(Function<T, K> idGetter) {
            this.idGetter = idGetter;
            return this;
        }

        public Builder<T, K> parentIdGetter(Function<T, K> parentIdGetter) {
            this.parentIdGetter = parentIdGetter;
            return this;
        }

        public Builder<T, K> childrenSetter(BiFunction<T, List<T>, T> childrenSetter) {
            this.childrenSetter = childrenSetter;
            return this;
        }

        public Builder<T, K> rootNodeFilter(Predicate<T> rootNodeFilter) {
            this.rootNodeFilter = rootNodeFilter;
            return this;
        }

        public Builder<T, K> defaultRootSupplier(Supplier<T> defaultRootSupplier) {
            this.defaultRootSupplier = defaultRootSupplier;
            return this;
        }

        public Builder<T, K> firstLevelNodeFilter(Predicate<T> firstLevelNodeFilter) {
            this.firstLevelNodeFilter = firstLevelNodeFilter;
            return this;
        }

        public Builder<T, K> parallelChecker(BooleanSupplier parallelChecker) {
            this.parallelChecker = parallelChecker;
            return this;
        }

        public TreeBuilder<T, K> build() {
            return new TreeBuilder<>(nodeList, idGetter, parentIdGetter, childrenSetter, rootNodeFilter, defaultRootSupplier, firstLevelNodeFilter, parallelChecker.getAsBoolean());
        }
    }
}
