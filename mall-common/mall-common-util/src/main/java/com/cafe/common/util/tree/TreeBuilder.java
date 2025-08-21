package com.cafe.common.util.tree;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeBuilder {

    public static <T, K> RecursiveBuilder.Builder<T, K> recursiveBuilder() {
        return new RecursiveBuilder.Builder<>();
    }

    public static <T, K> MapLookupBuilder.Builder<T, K> mapLookupBuilder() {
        return new MapLookupBuilder.Builder<>();
    }

    /**
     * 递归构建器
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RecursiveBuilder<T, K> {

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

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
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

            public RecursiveBuilder<T, K> build() {
                return new RecursiveBuilder<>(nodeList, idGetter, parentIdGetter, childrenSetter, rootNodeFilter, defaultRootSupplier, firstLevelNodeFilter, parallelChecker.getAsBoolean());
            }
        }
    }

    /**
     * Map 查询构建器
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MapLookupBuilder<T, K> {

        /**
         * 节点列表
         */
        private final List<T> nodeList;

        /**
         * 节点ID
         */
        private final K id;

        /**
         * 父节点ID
         */
        private final K parentId;

        /**
         * 获取节点ID的方法
         */
        private final Function<T, K> idGetter;

        /**
         * 获取节点父ID的方法
         */
        private final Function<T, K> parentIdGetter;

        /**
         * 获取节点子节点的方法
         */
        private final Function<T, List<T>> childrenGetter;

        /**
         * 设置节点子节点的方法
         */
        private final BiFunction<T, List<T>, T> childrenSetter;

        /**
         * 获取默认根节点的方法
         */
        private final Supplier<T> defaultRootSupplier;

        /**
         * 是否使用并行流
         */
        private final boolean parallel;

        /**
         * 构建树形节点
         *
         * @return 树形节点
         */
        public T buildTreeNode() {
            Map<K, T> nodeMap = initNodeMap();
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                .filter(Objects::nonNull)
                .peek(node -> Optional.of(node)
                    .map(parentIdGetter)
                    .map(nodeMap::get)
                    .map(childrenGetter)
                    .map(children -> children.add(node)))
                .filter(node -> Objects.equals(idGetter.apply(node), id))
                .collect(Collectors.toList())
                .stream()
                .findAny()
                .orElse(defaultRootSupplier.get());
        }

        /**
         * 构建树形列表
         *
         * @return 树形列表
         */
        public List<T> buildTreeList() {
            Map<K, T> nodeMap = initNodeMap();
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                .filter(Objects::nonNull)
                .peek(node -> Optional.of(node)
                    .map(parentIdGetter)
                    .map(nodeMap::get)
                    .map(childrenGetter)
                    .map(children -> children.add(node)))
                .filter(node -> Objects.equals(parentIdGetter.apply(node), parentId))
                .collect(Collectors.toList());
        }

        /**
         * 初始化节点集合
         *
         * @return 节点集合
         */
        private Map<K, T> initNodeMap() {
            return (parallel ? nodeList.parallelStream() : nodeList.stream())
                .map(node -> childrenSetter.apply(node, new ArrayList<>()))
                .collect(Collectors.toMap(idGetter, Function.identity()));
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Builder<T, K> {

            /**
             * 节点列表
             */
            private List<T> nodeList;

            /**
             * 节点ID
             */
            private K id = null;

            /**
             * 父节点ID
             */
            private K parentId = null;

            /**
             * 获取节点ID的方法
             */
            private Function<T, K> idGetter;

            /**
             * 获取节点父ID的方法
             */
            private Function<T, K> parentIdGetter;

            /**
             * 获取节点子节点的方法
             */
            private Function<T, List<T>> childrenGetter;

            /**
             * 设置节点子节点的方法
             */
            private BiFunction<T, List<T>, T> childrenSetter;

            /**
             * 获取默认根节点的方法
             */
            private Supplier<T> defaultRootSupplier;

            /**
             * 是否使用并行流的判断条件
             */
            private BooleanSupplier parallelChecker = () -> false;

            public Builder<T, K> nodeList(List<T> nodeList) {
                this.nodeList = nodeList;
                return this;
            }

            public Builder<T, K> id(K id) {
                this.id = id;
                return this;
            }

            public Builder<T, K> parentId(K parentId) {
                this.parentId = parentId;
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

            public Builder<T, K> childrenGetter(Function<T, List<T>> childrenGetter) {
                this.childrenGetter = childrenGetter;
                return this;
            }

            public Builder<T, K> childrenSetter(BiFunction<T, List<T>, T> childrenSetter) {
                this.childrenSetter = childrenSetter;
                return this;
            }

            public Builder<T, K> defaultRootSupplier(Supplier<T> defaultRootSupplier) {
                this.defaultRootSupplier = defaultRootSupplier;
                return this;
            }

            public Builder<T, K> parallelChecker(BooleanSupplier parallelChecker) {
                this.parallelChecker = parallelChecker;
                return this;
            }

            public MapLookupBuilder<T, K> build() {
                return new MapLookupBuilder<>(nodeList, id, parentId, idGetter, parentIdGetter, childrenGetter, childrenSetter, defaultRootSupplier, parallelChecker.getAsBoolean());
            }
        }
    }
}
