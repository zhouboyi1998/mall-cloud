package com.cafe.common.lang.date;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Project: mall-cloud
 * @Package: com.cafe.common.lang.date
 * @Author: zhouboyi
 * @Date: 2023/9/7 10:38
 * @Description:
 */
class PeriodTest {

    // -------------------- FIELD --------------------

    /**
     * 控制台打印颜色
     */
    private static final String WHITE = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";

    /**
     * 时间字符串
     */
    private static final String TEXT_1 = "1998-09-08 15:30:00";
    private static final String TEXT_2 = "2017-09-01 09:00:00";
    private static final String TEXT_3 = "2021-06-30 17:29:59";

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

    // -------------------- INIT --------------------

    static {
        initDatePeriod();
        initLocalDateTimePeriod();
    }

    @SneakyThrows
    private static void initDatePeriod() {
        Date date1 = DatePeriod.DATE_FORMATTER.get().parse(TEXT_1);
        Date date2 = DatePeriod.DATE_FORMATTER.get().parse(TEXT_2);
        Date date3 = DatePeriod.DATE_FORMATTER.get().parse(TEXT_3);
        date_period_1 = new DatePeriod(date1, date2);
        date_period_2 = new DatePeriod(date1, date2);
        date_period_3 = new DatePeriod(date2, date3);
    }

    private static void initLocalDateTimePeriod() {
        LocalDateTime localDateTime1 = LocalDateTime.parse(TEXT_1, LocalDateTimePeriod.LOCAL_DATE_TIME_FORMATTER);
        LocalDateTime localDateTime2 = LocalDateTime.parse(TEXT_2, LocalDateTimePeriod.LOCAL_DATE_TIME_FORMATTER);
        LocalDateTime localDateTime3 = LocalDateTime.parse(TEXT_3, LocalDateTimePeriod.LOCAL_DATE_TIME_FORMATTER);
        local_date_time_period_1 = new LocalDateTimePeriod(localDateTime1, localDateTime2);
        local_date_time_period_2 = new LocalDateTimePeriod(localDateTime1, localDateTime2);
        local_date_time_period_3 = new LocalDateTimePeriod(localDateTime2, localDateTime3);
    }

    // -------------------- EQUALS --------------------

    @Test
    void testDatePeriodEquals() {
        System.out.println(BLUE + "date_period_1" + WHITE + " equals " + BLUE + "date_period_2" + RED + " " + date_period_1.equals(date_period_2) + WHITE);
        System.out.println(BLUE + "date_period_1" + WHITE + " equals " + BLUE + "date_period_3" + RED + " " + date_period_1.equals(date_period_3) + WHITE);
    }

    @Test
    void testLocalDateTimePeriodEquals() {
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " equals " + GREEN + "local_date_time_period_2" + RED + " " + local_date_time_period_1.equals(local_date_time_period_2) + WHITE);
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " equals " + GREEN + "local_date_time_period_3" + RED + " " + local_date_time_period_1.equals(local_date_time_period_3) + WHITE);
    }

    // -------------------- HASH CODE --------------------

    @Test
    void testDatePeriodHashCode() {
        System.out.println(BLUE + "date_period_1" + WHITE + " hash code " + RED + date_period_1.hashCode() + WHITE);
        System.out.println(BLUE + "date_period_2" + WHITE + " hash code " + RED + date_period_2.hashCode() + WHITE);
        System.out.println(BLUE + "date_period_3" + WHITE + " hash code " + RED + date_period_3.hashCode() + WHITE);
    }

    @Test
    void testLocalDateTimePeriodHashCode() {
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " hash code " + RED + local_date_time_period_1.hashCode() + WHITE);
        System.out.println(GREEN + "local_date_time_period_2" + WHITE + " hash code " + RED + local_date_time_period_2.hashCode() + WHITE);
        System.out.println(GREEN + "local_date_time_period_3" + WHITE + " hash code " + RED + local_date_time_period_3.hashCode() + WHITE);
    }

    // -------------------- TO STRING --------------------

    @Test
    void testDatePeriodToString() {
        System.out.println(BLUE + "date_period_1" + WHITE + " to string " + RED + date_period_1.toString() + WHITE);
    }

    @Test
    void testLocalDateTimePeriodToString() {
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " to string " + RED + local_date_time_period_1.toString() + WHITE);
    }

    // -------------------- TIMES --------------------

    @Test
    void testDatePeriodTimes() {
        String[] times = date_period_1.times();
        System.out.println(BLUE + "date_period_1" + WHITE + " start " + RED + times[0] + WHITE + " end " + RED + times[1] + WHITE);
    }

    @Test
    void testLocalDateTimePeriodTimes() {
        String[] times = local_date_time_period_1.times();
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " start " + RED + times[0] + WHITE + " end " + RED + times[1] + WHITE);
    }

    // -------------------- CONTENT EQUALS --------------------

    @Test
    void testDatePeriodContentEquals() {
        System.out.println(BLUE + "date_period_1" + WHITE + " content equals " + BLUE + "date_period_2" + RED + " " + date_period_1.contentEquals(date_period_2) + WHITE);
        System.out.println(BLUE + "date_period_1" + WHITE + " content equals " + BLUE + "date_period_3" + RED + " " + date_period_1.contentEquals(date_period_3) + WHITE);
    }

    @Test
    void testLocalDateTimePeriodContentEquals() {
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " content equals " + GREEN + "local_date_time_period_2" + RED + " " + local_date_time_period_1.contentEquals(local_date_time_period_2) + WHITE);
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " content equals " + GREEN + "local_date_time_period_3" + RED + " " + local_date_time_period_1.contentEquals(local_date_time_period_3) + WHITE);
    }

    @Test
    void testDifferentPeriodContentEquals() {
        System.out.println(BLUE + "date_period_1" + WHITE + " content equals " + GREEN + "local_date_time_period_1" + RED + " " + date_period_1.contentEquals(local_date_time_period_1) + WHITE);
        System.out.println(BLUE + "date_period_1" + WHITE + " content equals " + GREEN + "local_date_time_period_3" + RED + " " + date_period_1.contentEquals(local_date_time_period_3) + WHITE);
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " content equals " + BLUE + "date_period_1" + RED + " " + local_date_time_period_1.contentEquals(date_period_1) + WHITE);
        System.out.println(GREEN + "local_date_time_period_1" + WHITE + " content equals " + BLUE + "date_period_3" + RED + " " + local_date_time_period_1.contentEquals(date_period_3) + WHITE);
    }
}
