package com.cafe.common.mysql.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.mysql.util
 * @Author: zhouboyi
 * @Date: 2022/5/1 20:21
 * @Description: MyBatis-Plus 条件构造工具类 (只能构造简单条件)
 */
public class MyBatisPlusWrapperUtil {

    /**
     * Wrapper 类型枚举
     */
    private enum WrapperType {QUERY, UPDATE}

    /**
     * 构造 QueryWrapper 条件
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> createQueryWrapperByModel(T model) {
        return (QueryWrapper<T>) buildWrapperByModel(model, WrapperType.QUERY);
    }

    /**
     * 构造 UpdateWrapper 条件
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> UpdateWrapper<T> createUpdateWrapperByModel(T model) {
        return (UpdateWrapper<T>) buildWrapperByModel(model, WrapperType.UPDATE);
    }

    /**
     * 构造 Wrapper 条件
     *
     * @param model
     * @param wrapperType
     * @param <T>
     * @return
     */
    private static <T> AbstractWrapper buildWrapperByModel(T model, WrapperType wrapperType) {
        // 构造 QueryWrapper / UpdateWrapper
        AbstractWrapper wrapper;
        if (ObjectUtil.equal(wrapperType, WrapperType.QUERY)) {
            wrapper = new QueryWrapper<T>();
        } else {
            wrapper = new UpdateWrapper<T>();
        }

        // 获取所有属性
        Map<String, Field> fieldMap = ReflectUtil.getFieldMap(model.getClass());
        // 迭代器循环
        Iterator iterator = fieldMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            // 跳过 serialVersionUID 属性
            if ("serialVersionUID".equals(entry.getKey())) {
                continue;
            }
            // 获取属性
            Field field = (Field) entry.getValue();
            // 根据传入的对象和属性, 获取属性值
            Object fieldValue = ReflectUtil.getFieldValue(model, field);
            // 如果属性值不为空, 加入 Wrapper 条件中
            if (ObjectUtil.isNotEmpty(fieldValue)) {
                // 获取属性名
                String fieldName = StrUtil.toUnderlineCase(field.getName());
                // 如果属性是 String 类型, 使用 like 条件, 否则使用 eq 条件
                if (fieldValue instanceof String) {
                    wrapper.like(fieldName, fieldValue);
                } else {
                    wrapper.eq(fieldName, fieldValue);
                }
            }
        }

        return wrapper;
    }
}
