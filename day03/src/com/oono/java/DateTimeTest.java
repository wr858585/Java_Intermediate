package com.oono.java;

import org.junit.Test;

import java.util.Date;

/**
 * JDK8之前日期和时间的测试
 *
 *
 * @author oono
 * @date 2020 07 19
 */
public class DateTimeTest {

    /*
    java.util.Date类
        |--- java.sql.Date类（是java.util.Date类的子类）

    java.util.Date类
    1. 两个构造器的使用（怎么造java.util.Date类的对象）
    ① Date()：空参构造器，创建一个对应当前时间的Date对象（每次打印值[即toString()]会变化，因为currentTime一直在变化）
    ② Date(long time)：创建一个指定时间戳的Date对象
    2. 两个方法的使用（有什么用途）
    ① toString()方法：Date类重写过了，显示固定格式的时间：年月日时分秒
    （否则Object类中原方法：return getClass().getName() + "@" + Integer.toHexString(hashCode());）
    ② getTime()方法：获取当前Date对象对应的时间戳

    java.sql.Date类
    1. 对应着数据库中的日期类型的变量，平常不用，数据库时采用
    2. 如何实例化？--> 只有一个构造器Date(long time)，传入指定时间戳造对象
    3. sql.Date对象 --> util.Date对象：直接赋值thanks to polymorphic
    4. util.Date对象 --> sql.Date对象：

     */

    @Test
    public void method1(){
        //构造器一：Date() --> 创建了一个对应当前时间currentTime的Date对象
        Date date1 = new Date();
        System.out.println(date1);//Sun Jul 19 12:57:38 CST 2020
        System.out.println(date1.toString());//Sun Jul 19 12:57:38 CST 2020.回想：toString()方法怎么用的（显然Date类重写了自己的toString方法，输出这一时间格式）
        System.out.println(date1.getTime());//1595142131638
        //空参构造器造出来的Date对象，打印obj/调用toString()均会返回当前时间的时间戳，所以会一直变化，因为当前时间一直变化
        //构造器二：创建指定时间戳的Date对象
        Date date2 = new Date(1595142131638L);
        System.out.println(date2);//Sun Jul 19 15:02:11 CST 2020

        java.sql.Date date3 = new java.sql.Date(1231287072112L);
        System.out.println(date3);//2009-01-07

        //如何将java.sql.Date类的对象转换为java.util.Date类的对象？--> 借助多态（向上转型）
        Date date4 = new java.sql.Date(2312312213L);

        //如何将java.util.Date类的对象转换为java.sql.Date类的对象？
        //情况一：向下转型（前提：有的转）
        Date date5 = new java.sql.Date(221312312312L);//以多态的形式，具有了子类sql.Date类的结构，但声明为父类util.Date类
        java.sql.Date date6 = (java.sql.Date)date5;
        //如果不是以多态的形式，而就是一个单纯的父类java.util.Date的对象，则根本具备有子类java.sql.Date中的结构，不能做向下转型。如下：
        Date date7 = new Date();//一个单纯的sql.Date父类
        java.sql.Date date8 = (java.sql.Date)date7;//编译通过，运行报错：java.lang.ClassCastException: java.util.Date cannot be cast to java.sql.Date
        //情况二：
        Date date9 = new Date();
        java.sql.Date date10 = new java.sql.Date(date9.getTime());//秒吧，传入同样的时间戳即可

    }



    //1. System类中的currentTimeMillis()
    @Test
    public void method(){
        long time = System.currentTimeMillis();
        System.out.println(time);//表示当前时间和1970年1月1日0点的时间差（单位：毫秒）[时间戳]
    }



}
