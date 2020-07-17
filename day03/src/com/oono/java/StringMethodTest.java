package com.oono.java;

import org.junit.Test;

/**
 *
 *
 * @author oono
 * @date 2020 07 17
 */
public class StringMethodTest {
    /*
    1. int length()：返回字符串的长度：return value.length
    2. char charAt(int index)：返回指定索引处的字符：return value[index]
    3. boolean isEmpty()：判断是否是空字符串（value length是否等于0）：return value.length == 0
    4. String toLowerCase()：使用默认语言环境，将String中的所有字符转化为小写
    5. String toUpperCase()：使用默认语言环境，将String中的所有字符转化为大写
    6. String trim()：返回字符串的副本，忽略前导空白和尾部空白（用途：注册用户名等）
    7. boolean equals(Object obj)：比较字符串的内容是否相同（value属性）
    8. boolean equalsIgnoreCase(String anotherString)：与equals方法类似，但忽略大小写
    9. String concat(String str)：将指定字符串连接到字符串的结尾。equivalent to "+"
    10. int compareTo(String anotherString)：比较两个字符串的大小
    11. String substring(int beginIndex)：返回一个新的字符串，它是此字符串的从beginIndex开始截取的
    12. String substring(int beginIndex, int endIndex)：同上，但截取左闭右开的字符串。凡是index都是左闭右开，length当然数个数有几个这样

     */

    @Test
    public void test2() {
        String s1 = "HelloWorld";
        String s2 = "helloworld";
        System.out.println(s1.equals(s2));//false
        System.out.println(s1.equalsIgnoreCase(s2));//true

        String s3 = "abc";
        String s4 = s3.concat("def");
        System.out.println(s4);//abcdef

        String s5 = "abc";
        String s6 = new String("abe");
        System.out.println(s5.compareTo(s6));//-2，比较的是底层存储的数字，a是97，所以c是99，e是101。涉及到字符串排序（如通讯录排序）

        String s7 = "北京尚硅谷教育";
        String s8 = s7.substring(2);
        System.out.println(s7);//北京尚硅谷教育
        System.out.println(s8);//尚硅谷教育

        String s9 = s7.substring(2, 5);
        System.out.println(s7);//北京尚硅谷教育
        System.out.println(s9);//尚硅谷


    }


    @Test
    public void test1() {

        String s1 = "HelloWorld";
        System.out.println(s1.length());//10
        System.out.println(s1.charAt(0));//H
        System.out.println(s1.charAt(9));//d
//      System.out.println(s1.charAt(10)); StringIndexOutOfBoundException
        System.out.println(s1.isEmpty());//false

        String s2 = s1.toLowerCase();
        System.out.println(s2);//helloworld
        System.out.println(s1);//HelloWorld.s1是不可变的，仍然为原来的字符串（字符串的不可变性）

        String s3 = "  he  llo   world     ";
        String s4 = s3.trim();
        System.out.println("----" + s3 + "----");//----  he  llo   world     ----
        System.out.println("----" + s4 + "----");//----he  llo   world----


    }
}
