package com.cafe.common.util.reflection;

import java.lang.reflect.Modifier;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.reflection
 * @Author: zhouboyi
 * @Date: 2025/10/21 10:21
 * @Description: Modifier 工具类扩展
 */
public class Modifiers {

    public static boolean isPublic(int mod) {
        return Modifier.isPublic(mod);
    }

    public static boolean nonPublic(int mod) {
        return !Modifier.isPublic(mod);
    }

    public static boolean isPrivate(int mod) {
        return Modifier.isPrivate(mod);
    }

    public static boolean nonPrivate(int mod) {
        return !Modifier.isPrivate(mod);
    }

    public static boolean isProtected(int mod) {
        return Modifier.isProtected(mod);
    }

    public static boolean nonProtected(int mod) {
        return !Modifier.isProtected(mod);
    }

    public static boolean isStatic(int mod) {
        return Modifier.isStatic(mod);
    }

    public static boolean nonStatic(int mod) {
        return !Modifier.isStatic(mod);
    }

    public static boolean isFinal(int mod) {
        return Modifier.isFinal(mod);
    }

    public static boolean nonFinal(int mod) {
        return !Modifier.isFinal(mod);
    }

    public static boolean isSynchronized(int mod) {
        return Modifier.isSynchronized(mod);
    }

    public static boolean nonSynchronized(int mod) {
        return !Modifier.isSynchronized(mod);
    }

    public static boolean isVolatile(int mod) {
        return Modifier.isVolatile(mod);
    }

    public static boolean nonVolatile(int mod) {
        return !Modifier.isVolatile(mod);
    }

    public static boolean isTransient(int mod) {
        return Modifier.isTransient(mod);
    }

    public static boolean nonTransient(int mod) {
        return !Modifier.isTransient(mod);
    }

    public static boolean isNative(int mod) {
        return Modifier.isNative(mod);
    }

    public static boolean nonNative(int mod) {
        return !Modifier.isNative(mod);
    }

    public static boolean isInterface(int mod) {
        return Modifier.isInterface(mod);
    }

    public static boolean nonInterface(int mod) {
        return !Modifier.isInterface(mod);
    }

    public static boolean isAbstract(int mod) {
        return Modifier.isAbstract(mod);
    }

    public static boolean nonAbstract(int mod) {
        return !Modifier.isAbstract(mod);
    }

    public static boolean isStrict(int mod) {
        return Modifier.isStrict(mod);
    }

    public static boolean nonStrict(int mod) {
        return !Modifier.isStrict(mod);
    }

    public static boolean isDefault(int mod) {
        return !Modifier.isPublic(mod) && !Modifier.isPrivate(mod) && !Modifier.isProtected(mod);
    }

    public static boolean nonDefault(int mod) {
        return Modifier.isPublic(mod) || Modifier.isPrivate(mod) || Modifier.isProtected(mod);
    }

    public static boolean isConstant(int mod) {
        return Modifier.isStatic(mod) && Modifier.isFinal(mod);
    }

    public static boolean nonConstant(int mod) {
        return !Modifier.isStatic(mod) || !Modifier.isFinal(mod);
    }
}
