package com.cafe.common.lang.date;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2023/9/7 10:38
 * @Description:
 */
class PeriodTest {

    // -------------------- CONSTANT --------------------

    /**
     * 控制台打印颜色
     */
    // DEFAULT: 重置颜色 (将颜色重置成默认颜色, 以免影响后续的日志打印颜色)
    private static final String DEFAULT = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    /**
     * 日期时间字符串
     */
    private static final String DATE_TIME_TEXT_1 = "1998-09-08 15:30:00";
    private static final String DATE_TIME_TEXT_2 = "2017-09-01 09:00:00";
    private static final String DATE_TIME_TEXT_3 = "2021-06-30 17:29:59";

    /**
     * 日期格式字符串
     */
    private static final String DATE_TEXT_1 = "1998-09-08";
    private static final String DATE_TEXT_2 = "2017-09-01";
    private static final String DATE_TEXT_3 = "2021-06-30";

    /**
     * 时间格式字符串
     */
    private static final String TIME_TEXT_1 = "15:30:00";
    private static final String TIME_TEXT_2 = "09:00:00";
    private static final String TIME_TEXT_3 = "17:29:59";

    // -------------------- VARIABLE --------------------

    /**
     * DatePeriod
     */
    private static DatePeriod date_period_1;
    private static DatePeriod date_period_2;
    private static DatePeriod date_period_3;

    /**
     * LocalDateTimePeriod
     */
    private static LocalDateTimePeriod local_date_time_period_1;
    private static LocalDateTimePeriod local_date_time_period_2;
    private static LocalDateTimePeriod local_date_time_period_3;

    /**
     * LocalDatePeriod
     */
    private static LocalDatePeriod local_date_period_1;
    private static LocalDatePeriod local_date_period_2;
    private static LocalDatePeriod local_date_period_3;

    /**
     * LocalTimePeriod
     */
    private static LocalTimePeriod local_time_period_1;
    private static LocalTimePeriod local_time_period_2;
    private static LocalTimePeriod local_time_period_3;

    // -------------------- INIT --------------------

    static {
        initDatePeriod();
        initLocalDateTimePeriod();
        initLocalDatePeriod();
        initLocalTimePeriod();
    }

    @SneakyThrows
    private static void initDatePeriod() {
        Date date1 = DatePeriod.DATE_FORMATTER.get().parse(DATE_TIME_TEXT_1);
        Date date2 = DatePeriod.DATE_FORMATTER.get().parse(DATE_TIME_TEXT_2);
        Date date3 = DatePeriod.DATE_FORMATTER.get().parse(DATE_TIME_TEXT_3);
        date_period_1 = new DatePeriod(date1, date2);
        date_period_2 = new DatePeriod(date1, date2);
        date_period_3 = new DatePeriod(date2, date3);
    }

    private static void initLocalDateTimePeriod() {
        LocalDateTime localDateTime1 = LocalDateTime.parse(DATE_TIME_TEXT_1, LocalDateTimePeriod.LOCAL_DATE_TIME_FORMATTER);
        LocalDateTime localDateTime2 = LocalDateTime.parse(DATE_TIME_TEXT_2, LocalDateTimePeriod.LOCAL_DATE_TIME_FORMATTER);
        LocalDateTime localDateTime3 = LocalDateTime.parse(DATE_TIME_TEXT_3, LocalDateTimePeriod.LOCAL_DATE_TIME_FORMATTER);
        local_date_time_period_1 = new LocalDateTimePeriod(localDateTime1, localDateTime2);
        local_date_time_period_2 = new LocalDateTimePeriod(localDateTime1, localDateTime2);
        local_date_time_period_3 = new LocalDateTimePeriod(localDateTime2, localDateTime3);
    }

    private static void initLocalDatePeriod() {
        LocalDate localDate1 = LocalDate.parse(DATE_TEXT_1, LocalDatePeriod.LOCAL_DATE_FORMATTER);
        LocalDate localDate2 = LocalDate.parse(DATE_TEXT_2, LocalDatePeriod.LOCAL_DATE_FORMATTER);
        LocalDate localDate3 = LocalDate.parse(DATE_TEXT_3, LocalDatePeriod.LOCAL_DATE_FORMATTER);
        local_date_period_1 = new LocalDatePeriod(localDate1, localDate2);
        local_date_period_2 = new LocalDatePeriod(localDate1, localDate2);
        local_date_period_3 = new LocalDatePeriod(localDate2, localDate3);
    }

    private static void initLocalTimePeriod() {
        LocalTime localTime1 = LocalTime.parse(TIME_TEXT_1, LocalTimePeriod.LOCAL_TIME_FORMATTER);
        LocalTime localTime2 = LocalTime.parse(TIME_TEXT_2, LocalTimePeriod.LOCAL_TIME_FORMATTER);
        LocalTime localTime3 = LocalTime.parse(TIME_TEXT_3, LocalTimePeriod.LOCAL_TIME_FORMATTER);
        local_time_period_1 = new LocalTimePeriod(localTime1, localTime2);
        local_time_period_2 = new LocalTimePeriod(localTime1, localTime2);
        local_time_period_3 = new LocalTimePeriod(localTime2, localTime3);
    }

    // -------------------- EQUALS --------------------

    @Test
    void testDatePeriodEquals() {
        System.out.println(BLUE + "date_period_1" + DEFAULT + " equals " + BLUE + "date_period_2" + RED + " " + date_period_1.equals(date_period_2) + DEFAULT);
        System.out.println(BLUE + "date_period_1" + DEFAULT + " equals " + BLUE + "date_period_3" + RED + " " + date_period_1.equals(date_period_3) + DEFAULT);
    }

    @Test
    void testLocalDateTimePeriodEquals() {
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " equals " + GREEN + "local_date_time_period_2" + RED + " " + local_date_time_period_1.equals(local_date_time_period_2) + DEFAULT);
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " equals " + GREEN + "local_date_time_period_3" + RED + " " + local_date_time_period_1.equals(local_date_time_period_3) + DEFAULT);
    }

    @Test
    void testLocalDatePeriodEquals() {
        System.out.println(PURPLE + "local_date_period_1" + DEFAULT + " equals " + PURPLE + "local_date_period_2" + RED + " " + local_date_period_1.equals(local_date_period_2) + DEFAULT);
        System.out.println(PURPLE + "local_date_period_1" + DEFAULT + " equals " + PURPLE + "local_date_period_3" + RED + " " + local_date_period_1.equals(local_date_period_3) + DEFAULT);
    }

    @Test
    void testLocalTimePeriodEquals() {
        System.out.println(YELLOW + "local_time_period_1" + DEFAULT + " equals " + YELLOW + "local_time_period_2" + RED + " " + local_time_period_1.equals(local_time_period_2) + DEFAULT);
        System.out.println(YELLOW + "local_time_period_1" + DEFAULT + " equals " + YELLOW + "local_time_period_3" + RED + " " + local_time_period_1.equals(local_time_period_3) + DEFAULT);
    }

    // -------------------- HASH CODE --------------------

    @Test
    void testDatePeriodHashCode() {
        System.out.println(BLUE + "date_period_1" + DEFAULT + " hash code " + RED + date_period_1.hashCode() + DEFAULT);
        System.out.println(BLUE + "date_period_2" + DEFAULT + " hash code " + RED + date_period_2.hashCode() + DEFAULT);
        System.out.println(BLUE + "date_period_3" + DEFAULT + " hash code " + RED + date_period_3.hashCode() + DEFAULT);
    }

    @Test
    void testLocalDateTimePeriodHashCode() {
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " hash code " + RED + local_date_time_period_1.hashCode() + DEFAULT);
        System.out.println(GREEN + "local_date_time_period_2" + DEFAULT + " hash code " + RED + local_date_time_period_2.hashCode() + DEFAULT);
        System.out.println(GREEN + "local_date_time_period_3" + DEFAULT + " hash code " + RED + local_date_time_period_3.hashCode() + DEFAULT);
    }

    @Test
    void testLocalDatePeriodHashCode() {
        System.out.println(PURPLE + "local_date_period_1" + DEFAULT + " hash code " + RED + local_date_period_1.hashCode() + DEFAULT);
        System.out.println(PURPLE + "local_date_period_2" + DEFAULT + " hash code " + RED + local_date_period_2.hashCode() + DEFAULT);
        System.out.println(PURPLE + "local_date_period_3" + DEFAULT + " hash code " + RED + local_date_period_3.hashCode() + DEFAULT);
    }

    @Test
    void testLocalTimePeriodHashCode() {
        System.out.println(YELLOW + "local_time_period_1" + DEFAULT + " hash code " + RED + local_time_period_1.hashCode() + DEFAULT);
        System.out.println(YELLOW + "local_time_period_2" + DEFAULT + " hash code " + RED + local_time_period_2.hashCode() + DEFAULT);
        System.out.println(YELLOW + "local_time_period_3" + DEFAULT + " hash code " + RED + local_time_period_3.hashCode() + DEFAULT);
    }

    // -------------------- TO STRING --------------------

    @Test
    void testDatePeriodToString() {
        System.out.println(BLUE + "date_period_1" + DEFAULT + " to string " + RED + date_period_1.toString() + DEFAULT);
    }

    @Test
    void testLocalDateTimePeriodToString() {
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " to string " + RED + local_date_time_period_1.toString() + DEFAULT);
    }

    @Test
    void testLocalDatePeriodToString() {
        System.out.println(PURPLE + "local_date_period_1" + DEFAULT + " to string " + RED + local_date_period_1.toString() + DEFAULT);
    }

    @Test
    void testLocalTimePeriodToString() {
        System.out.println(YELLOW + "local_time_period_1" + DEFAULT + " to string " + RED + local_time_period_1.toString() + DEFAULT);
    }

    // -------------------- TIMES --------------------

    @Test
    void testDatePeriodTimes() {
        String[] times = date_period_1.times();
        System.out.println(BLUE + "date_period_1" + DEFAULT + " start " + RED + times[0] + DEFAULT + " end " + RED + times[1] + DEFAULT);
    }

    @Test
    void testLocalDateTimePeriodTimes() {
        String[] times = local_date_time_period_1.times();
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " start " + RED + times[0] + DEFAULT + " end " + RED + times[1] + DEFAULT);
    }

    @Test
    void testLocalDatePeriodTimes() {
        String[] times = local_date_period_1.times();
        System.out.println(PURPLE + "local_date_period_1" + DEFAULT + " start " + RED + times[0] + DEFAULT + " end " + RED + times[1] + DEFAULT);
    }

    @Test
    void testLocalTimePeriodTimes() {
        String[] times = local_time_period_1.times();
        System.out.println(YELLOW + "local_time_period_1" + DEFAULT + " start " + RED + times[0] + DEFAULT + " end " + RED + times[1] + DEFAULT);
    }

    // -------------------- CONTENT EQUALS --------------------

    @Test
    void testDatePeriodContentEquals() {
        System.out.println(BLUE + "date_period_1" + DEFAULT + " content equals " + BLUE + "date_period_2" + RED + " " + date_period_1.contentEquals(date_period_2) + DEFAULT);
        System.out.println(BLUE + "date_period_1" + DEFAULT + " content equals " + BLUE + "date_period_3" + RED + " " + date_period_1.contentEquals(date_period_3) + DEFAULT);
    }

    @Test
    void testLocalDateTimePeriodContentEquals() {
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " content equals " + GREEN + "local_date_time_period_2" + RED + " " + local_date_time_period_1.contentEquals(local_date_time_period_2) + DEFAULT);
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " content equals " + GREEN + "local_date_time_period_3" + RED + " " + local_date_time_period_1.contentEquals(local_date_time_period_3) + DEFAULT);
    }

    @Test
    void testLocalDatePeriodContentEquals() {
        System.out.println(PURPLE + "local_date_period_1" + DEFAULT + " content equals " + PURPLE + "local_date_period_2" + RED + " " + local_date_period_1.contentEquals(local_date_period_2) + DEFAULT);
        System.out.println(PURPLE + "local_date_period_1" + DEFAULT + " content equals " + PURPLE + "local_date_period_3" + RED + " " + local_date_period_1.contentEquals(local_date_period_3) + DEFAULT);
    }

    @Test
    void testLocalTimePeriodContentEquals() {
        System.out.println(YELLOW + "local_time_period_1" + DEFAULT + " content equals " + YELLOW + "local_time_period_2" + RED + " " + local_time_period_1.contentEquals(local_time_period_2) + DEFAULT);
        System.out.println(YELLOW + "local_time_period_1" + DEFAULT + " content equals " + YELLOW + "local_time_period_3" + RED + " " + local_time_period_1.contentEquals(local_time_period_3) + DEFAULT);
    }

    @Test
    void testDifferentPeriodContentEquals() {
        System.out.println(BLUE + "date_period_1" + DEFAULT + " content equals " + GREEN + "local_date_time_period_1" + RED + " " + date_period_1.contentEquals(local_date_time_period_1) + DEFAULT);
        System.out.println(BLUE + "date_period_1" + DEFAULT + " content equals " + GREEN + "local_date_time_period_3" + RED + " " + date_period_1.contentEquals(local_date_time_period_3) + DEFAULT);
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " content equals " + BLUE + "date_period_1" + RED + " " + local_date_time_period_1.contentEquals(date_period_1) + DEFAULT);
        System.out.println(GREEN + "local_date_time_period_1" + DEFAULT + " content equals " + BLUE + "date_period_3" + RED + " " + local_date_time_period_1.contentEquals(date_period_3) + DEFAULT);
    }
}
