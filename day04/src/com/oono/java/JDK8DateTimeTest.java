package com.oono.java;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * JKD8中日期时间API的测试
 *
 * @author oono
 * @date 2020 07 21
 */
public class JDK8DateTimeTest {

    @Test
    public void testDate(){
        //Date类中的偏移量
        Date date1 = new Date(2020,9,8);
        System.out.println(date1);//Fri Oct 08 00:00:00 CST 3920，明显不对，因为源码中有偏移
        Date date2 = new Date(2020-1900,9-1,8);
        System.out.println(date2);//Tue Sep 08 00:00:00 CST 2020，才是对的
    }

    /*
    LocalDate, LocalTime, LocalDateTime的使用

    说明：
    1. LocalDateTime相较于另外两个使用频率更高
    2. 类似于Calendar类，但简洁好用很多

     */

    @Test
    public void test1(){

        //1. 实例化，方式简单好记（调用两种静态方法，而非构造器）

        //1.1 方式一：调用now()方法，来获取当前的日期/时间/日期时间（静态方法）
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);//2020-07-22
        System.out.println(localTime);//09:41:59.498
        System.out.println(localDateTime);//2020-07-22T09:41:59.498

        //1.2 方式二：调用of()方法，来获取指定的日期/时间/日期时间（静态方法）
        LocalDateTime localDateTime1 = LocalDateTime.of(2020,10,6,13,24,43);
        System.out.println(localDateTime1);//2020-10-06T13:24:43

        //2. 相关操作/方法

        //2.1 getXxx()：获取相关属性
        System.out.println(localDateTime.getDayOfMonth());//22
        System.out.println(localDateTime.getDayOfWeek());//WEDNESDAY
        System.out.println(localDateTime.getMonth());//JULY
        System.out.println(localDateTime.getMonthValue());//7，不存在偏移量的问题，简单好用
        System.out.println(localDateTime.getMinute());//55

        //2.2 withXxx()：设置相关属性。有返回值，返回一个新的变量with modified value，而原变量不变 --> 不可变性（和Calendar的set()方法有本质不同，它将原变量改变了）
        LocalDate localDate1 = localDate.withDayOfMonth(30);
        System.out.println(localDate);//2020-07-22
        System.out.println(localDate1);//2020-07-30

        LocalDateTime localDateTime2 = localDateTime.withHour(4);
        System.out.println(localDateTime);//2020-07-22T10:19:11.472
        System.out.println(localDateTime2);//2020-07-22T04:19:11.472

        //2.3 plusXxx(), minusXxx()：有返回值，也体现了不可变性
        LocalDateTime localDateTime3 = localDateTime.plusMonths(3);
        System.out.println(localDateTime);//2020-07-22T10:40:27.830
        System.out.println(localDateTime3);//2020-10-22T10:40:27.830

        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
        System.out.println(localDateTime);//2020-07-22T10:41:54.699
        System.out.println(localDateTime4);//2020-07-16T10:41:54.699

    }

    /*
    Instant的使用 --> 类似于java.util.Date类
     */

    @Test
    public void test2(){
        //now()：获取本初子午线的格林威治时间（了解）
        Instant instant = Instant.now();
        System.out.println(instant);//2020-07-22T03:35:29.795Z（本初子午线格林威治时间，北京时间要+8小时）

        //添加时间的偏移量（了解）
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2020-07-22T11:46:28.028+08:00

        //toEpochMilli()：获取自1970年1月1日（UTC）开始的毫秒数[时间戳] --> 类比：Date类的getTime()
        long l = instant.toEpochMilli();
        System.out.println(l);//1595389588028

        //ofEpochMilli()：通过指定的毫秒数，获取Instance的实例 --> 类比：Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1595389588028L);
        System.out.println(instant1);//2020-07-22T03:46:28.028Z



    }

    /*
    DateTimeFormatter类的使用：格式化于解析 --> 类似于SimpleDateFormat类


     */

    @Test
    public void test3(){

        //1. 实例化

        // 方式一：预定义的标准格式，如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME（用的少）
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化：日期 --> 字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = formatter.format(localDateTime);
        System.out.println(localDateTime);//2020-07-22T16:06:35.173
        System.out.println(format);//2020-07-22T16:06:35.173
        //解析：字符串 --> 日期
        TemporalAccessor parse = formatter.parse("2020-07-22T16:06:35.173");
        System.out.println(parse);//{},ISO resolved to 2020-07-22T16:06:35.173

        //方式二：本地化相关的格式，如：ofLocalizedDateTime(FormatStyle.LONG)（用的少）
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        //格式化：
        String format1 = formatter1.format(localDateTime);
        String format2 = formatter2.format(localDateTime);
        String format3 = formatter3.format(localDateTime);

        System.out.println(format1);//20-7-22 下午4:17
        System.out.println(format2);//2020-7-22 16:19:44
        System.out.println(format3);//2020年7月22日 下午04时19分44秒

        //解析：略

        //[重点]方式三：自定义的格式，如：ofPattern("yyyy-MM-dd hh:mm:ss E")（用的多）
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format4 = formatter4.format(LocalDateTime.now());
        System.out.println(format4);//2020-07-22 04:26:16
        //解析
        TemporalAccessor parse1 = formatter4.parse("2020-07-22 04:26:16");
        System.out.println(parse1);//{SecondOfMinute=16, NanoOfSecond=0, HourOfAmPm=4, MicroOfSecond=0, MinuteOfHour=26, MilliOfSecond=0},ISO resolved to 2020-07-22

    }

}
