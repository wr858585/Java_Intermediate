package com.oono.java;

import org.junit.Test;

/**
 * String的使用
 *
 * @author oono
 * @date 2020 07 16
 */
public class StringTest {

    /*
    结论：
    1. 常量与常量的拼接结果在常量池中
    2. 只要其中有一个是变量，则结果在堆空间中
    3. 如果拼接的结果调用的是intern()方法，返回值就在常量池中

     */

    @Test
    public void test3(){
        String s1 = "javaEE";
        String s2 = "Hadoop";

        String s3 = "javaEEHadoop";
        String s4 = "javaEE" + "Hadoop";
        String s5 = s1 + "Hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;
        String s8 = s5.intern();//返回值得到的s8，使用的是常量池中已经存在的"javaEEHadoop"

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s5 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false
        System.out.println(s3 == s8);//true



    }


    /*
    String类的实例化方式：
    方式一： 通过字面量定义的方式
    方式二：通过new + 构造器的方式

    面试题：String s = new String("abc");
    这种方式来创建对象，实际上创建了几个对象？
    --> 2个，一个在heap中new的结构，一个是char型数组，对应常量池中新建的对象（"abc"）。[如果之前已经有了"abc"，则不会再在常量池中新建]


     */
    @Test
    public void test2(){

        //此时的s1和s2的数据javaEE是声明在方法区的字符串常量池中
        String s1 = "javaEE";
        String s2 = "javaEE";
        //通过new + 构造器的方式：此时的s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址值
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false

        System.out.println("****************************");

        Person p1 = new Person("Tom", 12);
        Person p2 = new Person("Tom", 12);

        System.out.println(p1.name == p2.name);//true，都是"Tom"字符串，在一个常量池中（除非我通过new的方式来定义的String）

        p1.name = "Jerry";
        System.out.println(p2.name);//"Tom"



    }





    /*
    String类：字符串
    1. 声明为final的，不可被继承
    2. 实现了Serializable接口：表示String是支持序列化的（IO流中的对象流）[通过实现Serialize接口，可以让对象进行传输，为实现这个接口的对象是无法传输的]
       实现了Comparable接口：表示String是可以比较大小的
    3. 在内部定义了final char value[]，用于存储字符串数据
    4. String代表一个“不可变的字符序列” -- 不可变性
            体现：① 当对字符串重新赋值时，会重新指定一块内存区域，不能使用原有的final char value[]进行赋值
                 ② 当对现有的字符串进行操作时，也需要重新指定内存区域，不能在原有的value上进行赋值
                 ③ 当调用String中的replace()方法时，修改指定的字符/字符串时，也必须重新指定内存区域，不能使用原有的value赋值
    5. 通过字面量的方式，给一个字符串赋值，此时字符串值声明在字符串常量池中，如"abc"
    6. 字符串常量池中是不会存储相同内容的字符串，如又声明一个"abc"，则直接指向常量池中已经有的"abc"，不会再去新建一个存放在常量池中

     */
    @Test
    public void test1() {
        String s1 = "abc";//字面量的定义方式
        String s2 = "abc";
//        s1 = "hello";

        System.out.println(s1 == s2);//比较s1和s2的地址值

        System.out.println(s1);//hello
        System.out.println(s2);//abc

        String s3 = "abc";
        s3 += "def";
        System.out.println(s3);//abcdef
        System.out.println(s2);//abc

        String s4 = "abc";
        String s5 = s4.replace('a', 'm');

        System.out.println(s4);//abc
        System.out.println(s5);//mbc

    }
}
