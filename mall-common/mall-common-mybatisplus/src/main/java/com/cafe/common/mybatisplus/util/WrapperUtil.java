package com.cafe.common.mybatisplus.util;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.lang.date.AbstractPeriod;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
        Field[] fields = model.getClass().getDeclaredFields();
        // 迭代器遍历所有属性
        for (Field field : fields) {
            // 跳过条件构造时忽略的属性
            if (IGNORE_FIELD_LIST.contains(field.getName())) {
                continue;
            }
            // 设置允许访问该属性 (反射时默认不允许访问私有的属性、方法、构造器)
            ReflectionUtils.makeAccessible(field);
            // 获取属性值
            Object fieldValue = ReflectionUtils.getField(field, model);
            // 如果属性值不为空, 加入 Wrapper 条件中
            if (ObjectUtils.isNotEmpty(fieldValue)) {
                // 将驼峰格式的属性名转换为下划线格式的字段名
                String column = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
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
                    if (ObjectUtils.isNotEmpty(start)) {
                        wrapper.gt((R) column, start);
                    }
                    if (ObjectUtils.isNotEmpty(end)) {
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
