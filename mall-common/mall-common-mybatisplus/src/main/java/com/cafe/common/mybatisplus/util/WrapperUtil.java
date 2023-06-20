package com.cafe.common.mybatisplus.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.lang.date.AbstractPeriod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.mybatisplus.util
 * @Author: zhouboyi
 * @Date: 2022/5/1 20:21
 * @Description: MyBatis-Plus 条件构造工具类
 */
public class WrapperUtil {

    /**
     * Wrapper 条件构造时忽略的属性
     */
    private static final List<String> IGNORE_FIELD_LIST = new ArrayList<>();

    static {
        IGNORE_FIELD_LIST.add(FieldConstant.SERIAL_VERSION_UID);
    }

    /**
     * 构造 QueryWrapper 条件
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> createQueryWrapper(T model) {
        return (QueryWrapper<T>) createWrapper(model, new QueryWrapper<>());
    }

    /**
     * 构造 Wrapper 条件
     *
     * @param model
     * @param wrapper
     * @param <T>
     * @param <R>
     * @param <Children>
     * @return
     */
    public static <T, R, Children extends AbstractWrapper<T, R, Children>> AbstractWrapper<T, R, Children> createWrapper(T model, AbstractWrapper<T, R, Children> wrapper) {
        // 获取所有属性
        Map<String, Field> fieldMap = ReflectUtil.getFieldMap(model.getClass());
        // 迭代器遍历所有属性
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            // 跳过条件构造时忽略的属性
            if (IGNORE_FIELD_LIST.contains(entry.getKey())) {
                continue;
            }
            // 获取属性
            Field field = entry.getValue();
            // 根据传入的对象和属性, 获取属性值
            Object fieldValue = ReflectUtil.getFieldValue(model, field);
            // 如果属性值不为空, 加入 Wrapper 条件中
            if (ObjectUtil.isNotEmpty(fieldValue)) {
                // 将驼峰格式的属性名转换为下划线格式的字段名
                String column = StrUtil.toUnderlineCase(field.getName());
                if (fieldValue instanceof String) {
                    // 字符串类型使用 like 条件
                    wrapper.like((R) column, fieldValue);
                } else if (fieldValue instanceof AbstractPeriod) {
                    // 转换字段名
                    column = column.replace(StringConstant.PERIOD, StringConstant.TIME);
                    // 获取时间区间
                    AbstractPeriod period = (AbstractPeriod) fieldValue;
                    Object start = period.getStart();
                    Object end = period.getEnd();
                    // 时间区间类型使用 gt / lt 条件
                    if (ObjectUtil.isNotEmpty(start)) {
                        wrapper.gt((R) column, start);
                    }
                    if (ObjectUtil.isNotEmpty(end)) {
                        wrapper.lt((R) column, end);
                    }
                } else {
                    // 其它类型使用 eq 条件
                    wrapper.eq((R) column, fieldValue);
                }
            }
        }
        return wrapper;
    }
}
