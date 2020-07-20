package com.oono.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JDK8之前的日期时间的API测试：
 * 1. System类中currentTimeMillis()
 * 2. java.util.Date和子类java.sql.Date
 * 3. SimpleDateFormat
 * 4. Calendar
 *
 *
 * @author oono
 * @date 2020 07 20
 */
public class DateTimeTest {

    /*
    SimpleDateFormat的使用：对日期Date类的格式化和解析（主要掌握这两个操作）
    1. 两个操作/功能
    ① 格式化：日期 --> 字符串
    ② 解析：字符串 --> 日期（格式化的逆过程）
    2. SimpleDateFormat的实例化

     */

    @Test
    public void testSimpleDateFormat() throws ParseException {

        //实例化方式一：空参构造器（不好用，因为日期格式不灵活，为默认给定模式）
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：日期 --> 字符串
        Date date = new Date();
        System.out.println(date);//Mon Jul 20 17:52:56 CST 2020，英文

        String formatDate = sdf.format(date);
        System.out.println(formatDate);//20-7-20 下午5:58（String类对象）

        //解析：字符串 --> 日期
        String str = "20-7-20 下午5:58";//由于解析是格式化的逆过程，所以格式的参照就把format()输出的日期时间格式抄过来，才行，其他格式不对。（空参SimpleDateFormat构造器不方便的地方）
        Date date1 = sdf.parse(str);
        System.out.println(date1);//Mon Jul 20 17:58:00 CST 2020（Date类对象）

        //实例化方式二：去JDK API中找SimpleDateFormat类中的用法说明，可以找到对应的构造器和参数释义
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化
        String formatDate1 = sdf1.format(date);
        System.out.println(formatDate1);//2020-07-20 19:18:04（String类对象）
        //解析：要求传入的字符串必须符合SimpleDateFormat识别的格式（通过构造器的参数体现，空参则默认格式），否则抛出ParseException
        Date date2 = sdf1.parse("2020-07-20 19:18:04");
        System.out.println(date2);//Mon Jul 20 19:18:04 CST 2020（Date类对象）

    }

    /*
    练习一：把字符串"2020-09-08"转换为java.sql.Date类型的数据（应用场景：后端将前端的字符串形式的时间，存入数据库中，就会有转换成java.sql.Date等类型数据的需求）

     */

//    @Test
//    public void test() throws ParseException {
//        String str = "2020-09-08";
//        SimpleDateFormat date = new SimpleDateFormat();
//        String format = date.format(str);
//        Date parseDate = date.parse(format);
//
//        java.sql.Date sqlDate = (java.sql.Date)parseDate;
//        System.out.println(sqlDate);
//    }


}
