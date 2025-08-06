package com.cafe.common.util.expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.expression
 * @Author: zhouboyi
 * @Date: 2025/8/6 15:51
 * @Description: SpEL 表达式工具类
 */
public class SpELUtil {

    /**
     * 评估 SpEL 表达式
     *
     * @param condition   SpEL 表达式
     * @param variableMap SpEL 表达式评估上下文的参数集合
     * @param resultType  SpEL 表达式评估结果类型 class 对象
     * @param <T>         SpEL 表达式评估结果类型
     * @return 评估结果
     */
    public static <T> T evaluate(String condition, Map<String, Object> variableMap, Class<T> resultType) {
        // 创建 SpEL 表达式评估上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 将参数添加到 SpEL 表达式评估上下文中
        variableMap.forEach(context::setVariable);
        // 评估 SpEL 表达式
        return evaluate(condition, context, resultType);
    }

    /**
     * 评估 SpEL 表达式
     *
     * @param condition  SpEL 表达式
     * @param context    SpEL 表达式评估上下文
     * @param resultType SpEL 表达式评估结果类型 class 对象
     * @param <T>        SpEL 表达式评估结果类型
     * @return 评估结果
     */
    public static <T> T evaluate(String condition, EvaluationContext context, Class<T> resultType) {
        // 创建 SpEL 表达式解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 解析 SpEL 表达式
        Expression expression = parser.parseExpression(condition);
        // 获取 SpEL 表达式评估结果
        return expression.getValue(context, resultType);
    }
}
