package com.cafe.common.util.collection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.collection
 * @Author: zhouboyi
 * @Date: 2025/4/27 14:12
 * @Description:
 */
class SetUtilTest {

    private static final Set<String> SET_1 = new HashSet<>();

    private static final Set<String> SET_2 = new HashSet<>();

    static {
        SET_1.add("Peter");
        SET_1.add("Mike");
        SET_1.add("John");

        SET_2.add("Peter");
        SET_2.add("Alice");
        SET_2.add("Bob");
    }

    // -------------------- union --------------------

    @Test
    void union() {
        Set<String> union = SetUtil.union(SET_1, SET_2, TreeSet::new);
        System.out.println(union);
    }

    @Test
    void unionList() {
        List<String> union = SetUtil.unionList(SET_1, SET_2);
        System.out.println(union);
    }

    @Test
    void unionSet() {
        Set<String> union = SetUtil.unionSet(SET_1, SET_2);
        System.out.println(union);
    }

    // -------------------- intersection --------------------

    @Test
    void intersection() {
        Set<String> intersection = SetUtil.intersection(SET_1, SET_2, TreeSet::new);
        System.out.println(intersection);
    }

    @Test
    void intersectionList() {
        List<String> intersection = SetUtil.intersectionList(SET_1, SET_2);
        System.out.println(intersection);
    }

    @Test
    void intersectionSet() {
        Set<String> intersection = SetUtil.intersectionSet(SET_1, SET_2);
        System.out.println(intersection);
    }

    // -------------------- difference --------------------

    @Test
    void difference() {
        Set<String> difference1 = SetUtil.difference(SET_1, SET_2, TreeSet::new);
        System.out.println(difference1);
        Set<String> difference2 = SetUtil.difference(SET_2, SET_1, TreeSet::new);
        System.out.println(difference2);
    }

    @Test
    void differenceList() {
        List<String> difference1 = SetUtil.differenceList(SET_1, SET_2);
        System.out.println(difference1);
        List<String> difference2 = SetUtil.differenceList(SET_2, SET_1);
        System.out.println(difference2);
    }

    @Test
    void differenceSet() {
        Set<String> difference1 = SetUtil.differenceSet(SET_1, SET_2);
        System.out.println(difference1);
        Set<String> difference2 = SetUtil.differenceSet(SET_2, SET_1);
        System.out.println(difference2);
    }
}
