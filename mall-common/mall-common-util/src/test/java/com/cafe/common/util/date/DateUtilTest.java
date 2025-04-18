package com.cafe.common.util.date;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.util.date
 * @Author: zhouboyi
 * @Date: 2025/4/18 23:17
 * @Description:
 */
class DateUtilTest {

    private static final String DATE_TIME_TEXT = "2021-06-30 17:30:01";

    private static final String DATE_TEXT = "2021-06-30";

    private static final String TIME_TEXT = "17:30:01";

    @Test
    void parseDate() {
        Date dateTime = DateUtil.parseDate(DATE_TIME_TEXT);
        Date date = DateUtil.parseDate(DATE_TEXT);
        Date time = DateUtil.parseDate(TIME_TEXT);

        System.out.println("dateTime -> " + DateFormatterHolder.DEFAULT_DATE_TIME_FORMATTER.get().format(dateTime));
        System.out.println("\tdate -> " + DateFormatterHolder.DEFAULT_DATE_TIME_FORMATTER.get().format(date));
        System.out.println("\ttime -> " + DateFormatterHolder.DEFAULT_DATE_TIME_FORMATTER.get().format(time));
    }

    @Test
    void parseCalendar() {
        Calendar dateTime = DateUtil.parseCalendar(DATE_TIME_TEXT);
        Calendar date = DateUtil.parseCalendar(DATE_TEXT);
        Calendar time = DateUtil.parseCalendar(TIME_TEXT);

        System.out.println("dateTime -> " + DateFormatterHolder.DEFAULT_DATE_TIME_FORMATTER.get().format(dateTime.getTime()));
        System.out.println("\tdate -> " + DateFormatterHolder.DEFAULT_DATE_TIME_FORMATTER.get().format(date.getTime()));
        System.out.println("\ttime -> " + DateFormatterHolder.DEFAULT_DATE_TIME_FORMATTER.get().format(time.getTime()));
    }
}
