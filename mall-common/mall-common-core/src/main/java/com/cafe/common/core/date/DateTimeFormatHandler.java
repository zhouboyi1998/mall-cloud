package com.cafe.common.core.date;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.enumeration.date.DateTimePatternEnum;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.date
 * @Author: zhouboyi
 * @Date: 2023/8/8 15:51
 * @Description: 日期时间格式化处理器
 */
@RestControllerAdvice(annotations = {RestController.class})
@Order(value = IntegerConstant.SIXTY)
public class DateTimeFormatHandler {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // 日期时间格式字符串转换成 LocalDateTime 类型
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern())));
            }
        });

        // 日期格式字符串转换成 LocalDate 类型
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE.getPattern())));
            }
        });

        // 时间格式字符串转换成 LocalTime 类型
        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_TIME.getPattern())));
            }
        });
    }
}
