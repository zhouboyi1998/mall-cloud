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
 * @Description: 日期时间格式化处理器
 */
@RestControllerAdvice(annotations = {RestController.class})
@Order(value = IntegerConstant.SIXTY)
public class DateTimeFormatHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeFormatHandler.class);

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // LocalDateTime 类型接收日期时间格式字符串
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern())));
            }
        });

        // LocalDate 类型接收日期格式字符串
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_DATE.getPattern())));
            }
        });

        // LocalTime 类型接收时间格式字符串
        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern(DateTimePatternEnum.DEFAULT_TIME.getPattern())));
            }
        });

        // Date 类型接收日期时间格式、日期格式、时间格式字符串
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                SimpleDateFormat format;
                Date date = new Date();
                try {
                    // 接收日期时间格式字符串
                    format = new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE_TIME.getPattern());
                    date = format.parse(text);
                } catch (ParseException e1) {
                    try {
                        // 接收日期格式字符串
                        format = new SimpleDateFormat(DateTimePatternEnum.DEFAULT_DATE.getPattern());
                        date = format.parse(text);
                    } catch (ParseException e2) {
                        try {
                            // 接收时间格式字符串
                            format = new SimpleDateFormat(DateTimePatternEnum.DEFAULT_TIME.getPattern());
                            Calendar instance = Calendar.getInstance();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(format.parse(text));
                            calendar.set(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DATE));
                            date = calendar.getTime();
                        } catch (ParseException e3) {
                            LOGGER.error("DateTimeFormatHandler.initBinder(): Can not parse the String text to Date type! text -> {}", text, e3);
                        }
                    }
                }
                setValue(date);
            }
        });
    }
}
