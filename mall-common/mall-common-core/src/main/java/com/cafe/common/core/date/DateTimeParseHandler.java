package com.cafe.common.core.date;

import com.cafe.common.constant.pool.IntegerConstant;
import com.cafe.common.enumeration.date.DateTimePatternEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.core.date
 * @Author: zhouboyi
 * @Date: 2023/8/8 15:51
 * @Description: 日期时间解析处理器
 */
@RestControllerAdvice(annotations = {RestController.class})
@Order(value = IntegerConstant.SIXTY)
public class DateTimeParseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeParseHandler.class);

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // 解析日期时间格式字符串成 LocalDateTime 类型
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern())));
            }
        });

        // 解析日期格式字符串成 LocalDate 类型
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE.getPattern())));
            }
        });

        // 解析时间格式字符串成 LocalTime 类型
        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_TIME.getPattern())));
            }
        });

        // 解析日期时间格式、日期格式、时间格式字符串成 Date 类型
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    // 解析日期时间格式字符串
                    setValue(new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern()).parse(text));
                } catch (ParseException e1) {
                    try {
                        // 解析日期格式字符串 (时间为 00:00:00)
                        setValue(new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE.getPattern()).parse(text));
                    } catch (ParseException e2) {
                        try {
                            // 解析时间格式字符串 (日期为当前年月日)
                            Calendar calendar = Calendar.getInstance();
                            int year = calendar.get(Calendar.YEAR);
                            int month = calendar.get(Calendar.MONTH);
                            int date = calendar.get(Calendar.DATE);
                            calendar.setTime(new SimpleDateFormat(DateTimePatternEnum.DEFAULT_TIME.getPattern()).parse(text));
                            calendar.set(year, month, date);
                            setValue(calendar.getTime());
                        } catch (ParseException e3) {
                            LOGGER.error("DateTimeFormatHandler.initBinder(): Can not parse the String text to Date type! text -> {}", text, e3);
                            throw new IllegalArgumentException("Can not parse the String text to Date type! text -> " + text);
                        }
                    }
                }
            }
        });

        // 解析日期时间格式、日期格式、时间格式字符串成 Calendar 类型
        binder.registerCustomEditor(Calendar.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Calendar calendar = Calendar.getInstance();
                try {
                    // 解析日期时间格式字符串
                    calendar.setTime(new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern()).parse(text));
                } catch (ParseException e1) {
                    try {
                        // 解析日期格式字符串
                        calendar.setTime(new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE.getPattern()).parse(text));
                    } catch (ParseException e2) {
                        try {
                            // 解析时间格式字符串
                            calendar.setTime(new SimpleDateFormat(DateTimePatternEnum.DEFAULT_TIME.getPattern()).parse(text));
                        } catch (ParseException e3) {
                            LOGGER.error("DateTimeFormatHandler.initBinder(): Can not parse the String text to Calendar type! text -> {}", text, e3);
                            throw new IllegalArgumentException("Can not parse the String text to Calendar type! text -> " + text);
                        }
                    }
                }
                setValue(calendar);
            }
        });
    }
}
