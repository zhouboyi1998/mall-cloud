package com.cafe.starter.boot.controlleradvice.type;

import com.cafe.common.constant.date.DateTimeConstant;
import com.cafe.common.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Calendar;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.starter.boot.controlleradvice.type
 * @Author: zhouboyi
 * @Date: 2023/8/8 15:51
 * @Description: 日期时间类型解析器
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
@Order(value = 60)
public class DateTimeTypeParser {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // 解析日期时间格式字符串成 LocalDateTime 类型
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_DATE_TIME)));
            }
        });

        // 解析日期格式字符串成 LocalDate 类型
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_DATE)));
            }
        });

        // 解析时间格式字符串成 LocalTime 类型
        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern(DateTimeConstant.DEFAULT_TIME)));
            }
        });

        // 解析日期时间格式、日期格式、时间格式字符串成 Date 类型
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(DateUtil.parseDate(text));
            }
        });

        // 解析日期时间格式、日期格式、时间格式字符串成 Calendar 类型
        binder.registerCustomEditor(Calendar.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(DateUtil.parseCalendar(text));
            }
        });
    }
}
