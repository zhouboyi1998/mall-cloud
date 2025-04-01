package com.cafe.infrastructure.mybatisplus.util;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.cafe.common.constant.app.FieldConstant;
import com.cafe.common.constant.pool.StringConstant;
import com.cafe.common.lang.date.AbstractPeriod;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.infrastructure.mybatisplus.util
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
     * 构造 QueryWrapper 查询条件
     *
     * @param model 查询条件
     * @param <T>   查询对象类型
     * @return 查询条件封装对象
     */
    public static <T> QueryWrapper<T> createQueryWrapper(T model) {
        return (QueryWrapper<T>) createWrapper(model, new QueryWrapper<>());
    }

    /**
     * 构造 LambdaQueryWrapper 查询条件
     *
     * @param model 查询条件
     * @param <T>   查询对象类型
     * @return 查询条件封装对象
     */
    public static <T> LambdaQueryWrapper<T> createLambdaQueryWrapper(T model) {
        return createQueryWrapper(model).lambda();
    }

    /**
     * 构造 Wrapper 条件
     *
     * @param model      查询条件
     * @param wrapper    查询条件封装对象
     * @param <T>        查询对象类型
     * @param <R>        字段类型
     * @param <Children> 查询条件封装对象类型
     * @return 查询条件封装对象
     */
    @SuppressWarnings("unchecked")
    public static <T, R, Children extends AbstractWrapper<T, R, Children>> AbstractWrapper<T, R, Children> createWrapper(
            T model, AbstractWrapper<T, R, Children> wrapper) {
        Class<?> modelClass = model.getClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(modelClass);
        if (tableInfo == null) {
            throw new RuntimeException("实体类未配置表信息");
        }

        // 从 TableInfo 获取逻辑删除列名
        String logicDeleteColumn = getLogicDeleteColumn(modelClass);

        // 遍历所有字段（通过 TableFieldInfo 确保使用数据库列名）
        for (TableFieldInfo tableField : tableInfo.getFieldList()) {
            // 跳过逻辑删除字段（后面统一处理）
            if (tableField.isLogicDelete()) {
                continue;
            }

            String fieldName = tableField.getProperty();
            String column = tableField.getColumn(); // 正确获取数据库列名

            if (IGNORE_FIELD_LIST.contains(fieldName)) {
                continue;
            }

            try {
                Field field = modelClass.getDeclaredField(fieldName);
                ReflectionUtils.makeAccessible(field);
                Object fieldValue = ReflectionUtils.getField(field, model);

                if (ObjectUtils.isNotEmpty(fieldValue)) {
                    if (fieldValue instanceof AbstractPeriod) {
                        handlePeriodCondition(wrapper, column, (AbstractPeriod) fieldValue);
                    } else if (fieldValue instanceof String) {
                        wrapper.like((R) column, fieldValue);
                    } else {
                        wrapper.eq((R) column, fieldValue);
                    }
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("字段不存在: " + fieldName, e);
            }
        }

        // 默认添加逻辑删除条件
        if (!hasDeletedCondition(wrapper, logicDeleteColumn)) {
            wrapper.eq((R) logicDeleteColumn, 0);
        }

        return wrapper;
    }

    /**
     * 获取逻辑删除字段的数据库列名
     */
    private static String getLogicDeleteColumn(Class<?> entityClass) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        if (tableInfo == null || tableInfo.getLogicDeleteFieldInfo() == null) {
            throw new RuntimeException("实体类未配置逻辑删除字段");
        }
        // 从逻辑删除字段信息中获取列名
        return tableInfo.getLogicDeleteFieldInfo().getColumn();
    }

    /**
     * 检查是否已存在逻辑删除条件（修复正则匹配问题）
     */
    private static <T, R, Children extends AbstractWrapper<T, R, Children>> boolean hasDeletedCondition(
            AbstractWrapper<T, R, Children> wrapper, String logicDeleteColumn) {
        String sqlSegment = wrapper.getSqlSegment();
        if (sqlSegment == null) {
            return false;
        }
        // 匹配列名时考虑可能的转义符（如反引号 ` 或引号 "）
        String pattern = String.format("(?i)(`?\\b%s\\b`?\\s*=\\s*0)", Pattern.quote(logicDeleteColumn));
        return sqlSegment.matches(".*" + pattern + ".*");
    }

    /**
     * 处理时间区间条件（如开始时间、结束时间）
     * @param wrapper       查询条件封装对象
     * @param column        数据库列名（如 create_time）
     * @param period        时间区间对象（包含 start 和 end）
     */
    private static <T, R, Children extends AbstractWrapper<T, R, Children>> void handlePeriodCondition(
            AbstractWrapper<T, R, Children> wrapper,
            String column,
            AbstractPeriod period) {

        // 1. 获取时间区间的开始和结束值
        Object start = period.getStart();
        Object end = period.getEnd();

        // 2. 添加条件：column > start（如果 start 非空）
        if (ObjectUtils.isNotEmpty(start)) {
            wrapper.gt((R) column, start);
        }

        // 3. 添加条件：column < end（如果 end 非空）
        if (ObjectUtils.isNotEmpty(end)) {
            wrapper.lt((R) column, end);
        }
    }

    /**
     * 构造空的 QueryWrapper 查询条件
     *
     * @param <T> 查询对象类型
     * @return 查询条件封装对象
     */
    public static <T> QueryWrapper<T> emptyQueryWrapper() {
        return new QueryWrapper<>();
    }

    /**
     * 构造空的 QueryWrapper 查询条件
     *
     * @param model 查询条件
     * @param <T>   查询对象类型
     * @return 查询条件封装对象
     */
    public static <T> QueryWrapper<T> emptyQueryWrapper(T model) {
        return new QueryWrapper<>(model);
    }

    /**
     * 构造空的 LambdaQueryWrapper 查询条件
     *
     * @param model 查询条件
     * @param <T>   查询对象类型
     * @return 查询条件封装对象
     */
    public static <T> LambdaQueryWrapper<T> emptyLambdaQueryWrapper(T model) {
        return emptyQueryWrapper(model).lambda();
    }
}
