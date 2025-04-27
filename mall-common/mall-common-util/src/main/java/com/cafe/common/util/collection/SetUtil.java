package com.cafe.common.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.collection
 * @Author: zhouboyi
 * @Date: 2025/4/27 10:10
 * @Description: Set 工具类
 */
public class SetUtil {

    // -------------------- union --------------------

    /**
     * 获取 collection1 与 collection2 的并集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param supplier    结果集合的构造方法
     * @param <E>         元素类型
     * @param <C>         集合类型
     * @return 并集
     */
    public static <E, C extends Collection<E>> C union(Collection<E> collection1, Collection<E> collection2, Supplier<C> supplier) {
        Set<E> set1 = new HashSet<>(collection1);
        set1.addAll(collection2);
        return set1.stream().collect(Collectors.toCollection(supplier));
    }

    /**
     * 获取 collection1 与 collection2 的并集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param <E>         元素类型
     * @return 并集 (集合类型: ArrayList)
     */
    public static <E> List<E> unionList(Collection<E> collection1, Collection<E> collection2) {
        return union(collection1, collection2, ArrayList::new);
    }

    /**
     * 获取 collection1 与 collection2 的并集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param <E>         元素类型
     * @return 并集 (集合类型: HashSet)
     */
    public static <E> Set<E> unionSet(Collection<E> collection1, Collection<E> collection2) {
        return union(collection1, collection2, HashSet::new);
    }

    // -------------------- intersection --------------------

    /**
     * 获取 collection1 与 collection2 的交集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param supplier    结果集合的构造方法
     * @param <E>         元素类型
     * @param <C>         集合类型
     * @return 交集
     */
    public static <E, C extends Collection<E>> C intersection(Collection<E> collection1, Collection<E> collection2, Supplier<C> supplier) {
        Set<E> set1 = new HashSet<>(collection1);
        set1.retainAll(collection2);
        return set1.stream().collect(Collectors.toCollection(supplier));
    }

    /**
     * 获取 collection1 与 collection2 的交集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param <E>         元素类型
     * @return 交集 (集合类型: ArrayList)
     */
    public static <E> List<E> intersectionList(Collection<E> collection1, Collection<E> collection2) {
        return intersection(collection1, collection2, ArrayList::new);
    }

    /**
     * 获取 collection1 与 collection2 的交集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param <E>         元素类型
     * @return 交集 (集合类型: HashSet)
     */
    public static <E> Set<E> intersectionSet(Collection<E> collection1, Collection<E> collection2) {
        return intersection(collection1, collection2, HashSet::new);
    }

    // -------------------- difference --------------------

    /**
     * 获取 collection1 与 collection2 的差集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param supplier    结果集合的构造方法
     * @param <E>         元素类型
     * @param <C>         集合类型
     * @return 差集
     */
    public static <E, C extends Collection<E>> C difference(Collection<E> collection1, Collection<E> collection2, Supplier<C> supplier) {
        Set<E> set1 = new HashSet<>(collection1);
        set1.removeAll(collection2);
        return set1.stream().collect(Collectors.toCollection(supplier));
    }

    /**
     * 获取 collection1 与 collection2 的差集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param <E>         元素类型
     * @return 差集 (集合类型: ArrayList)
     */
    public static <E> List<E> differenceList(Collection<E> collection1, Collection<E> collection2) {
        return difference(collection1, collection2, ArrayList::new);
    }

    /**
     * 获取 collection1 与 collection2 的差集
     *
     * @param collection1 操作集合1
     * @param collection2 操作集合2
     * @param <E>         元素类型
     * @return 差集 (集合类型: HashSet)
     */
    public static <E> Set<E> differenceSet(Collection<E> collection1, Collection<E> collection2) {
        return difference(collection1, collection2, HashSet::new);
    }
}
