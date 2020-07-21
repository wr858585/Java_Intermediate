package com.oono.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    练习二："三天打鱼两天晒网" 1990-01-01 xxxx-xx-xx 渔夫是在打鱼还是晒网？
    举例：2020-09-08
    需要先计算总天数
    总天数 % 5 == 1，2，3 --> 打鱼
    总天数 % 5 == 4, 0 --> 晒网
    总天数的计算：
    方式一：把两个String转换成Date类的obj，调用getTime()方法获取时间戳，二者相减得到总共的秒数，再换算成天数。（存在精度损失问题，要+1天）
     */

    @Test
    public void test() throws ParseException {
        String str = "2020-09-08";
        //为调用SimpleDateFormat类中的非静态方法parse()对str进行解析
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //调用parse()方法，把str解析成Date类的数据
        Date parse = sdf.parse(str);

/*
        java.sql.Date sqlDate = (java.sql.Date)parse;
        System.out.println(sqlDate);
        这种方式就错了。原因：Date父类根本就没有子类java.sql.Date的结构，所以向下强转必定失败
        报错：java.lang.ClassCastException: java.util.Date cannot be cast to java.sql.Date
*/

        //调用java.sql.Date的唯一构造器java.sql.Date(long time)来造对象，time为时间戳。由于已经把String类的时间解析为Date类的对象parse，所以用parse调用Date类的结构getTime()即可得到时间戳
        java.sql.Date date = new java.sql.Date(parse.getTime());
        System.out.println(date);//2020-09-08

    }

    /*
    Calendar日历类的使用
    1. 如何实例化：
    ① 方式一：由于Calendar类是抽象类，所以需要借助子类GregorianCalendar来造对象
    ② 方式二：调用Calendar类的静态方法getInstance()
    2. 常用操作（方法）
    ① get()
    ②
     */

    @Test
    public void testCalendar(){

        //1. 实例化
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());

        //2. 常用方法

        //get()：返回指定Calendar抽象类中定义的static属性（由于是静态属性，所以实例或Calendar类都可以获取到）
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);//21
        System.out.println(calendar.get(calendar.DAY_OF_YEAR));//203
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));//3

        //set()：设置这些static属性（非final，所以可以改）
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//22

        //add()：修改这些static属性（正数+，负数-）
        calendar.add(calendar.DAY_OF_MONTH, -2);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//20

        //getTime()：相当于把Calendar类的对象 --> Date类的对象
        Date date = calendar.getTime();
        System.out.println(date);//Mon Jul 20 16:11:59 CST 2020

        //setTime()：相当于把Date类 --> Calendar类
        Date date1 = new Date();
        calendar.setTime(date1);
        System.out.println(calendar.get(calendar.DAY_OF_MONTH));//21，因为Date()空参构造器造的是当前的时间



    }




}
