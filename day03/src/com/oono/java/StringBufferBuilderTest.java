package com.oono.java;

import org.junit.Test;

/**
 * 关于StringBuffer和StringBuilder的使用
 *
 *
 *
 * @author oono
 * @date 2020 07 18
 */
public class StringBufferBuilderTest {

    /*
    StringBuffer的常用方法（StringBuilder一样）
    1. StringBuffer append(xxx)：提供了很多的append()方法，用于进行字符串的拼接
    2. StringBuffer delete(int start, int end)：删除指定位置的内容
    3. StringBuffer replace(int start, int end, String str)：把[start,end]的位置内容替换为str
    4. StringBuffer insert(int offset, xxx)：在指定index处插入各种类型的数据（xxx表示很多种数类，因为提供了很多重在的insert()方法）
    5. StringBuffer reverse()：反转当前字符序列（毕竟StringBuffer不叫字符串了嘛）
    之前讲过的String也有的方法（除了setCharAt，因为String为不可变序列嘛，怎么set)
    6. public int indexOf(String str)
    7. public String substring(int start, int end)
    8. public int length()
    9. public char charAt(int n)
    10. public void setCharAt(int n, char ch)
    总结：
    增：append(xxx),
    删：delete(int start, int end)
    改：replace(int start, int end, String str)[改多]/setCharAt(int n, char ch)[改单]
    查：charAt(int n)[查字符=?]/indexOf(String str)[查索引=?]
    插：insert(int offset, xxx)
    长度：length()
    遍历：for loop + charAt()结合使用（用的少，其实直接调用StringBuffer的toString()方法即可）

     */

    @Test
    public void test1(){

        StringBuffer s1 = new StringBuffer("abc");
        s1.append(1);
        s1.append("1");
        s1.append('1');//append()重载的方法很多，各种数类好多都允许添加进去
        System.out.println(s1);//abc111

        s1.delete(2,5);
        System.out.println(s1);//ab1，左闭右开

        s1.replace(2,20,"hello");
        System.out.println(s1);//abhello

        s1.insert(2, false);
        System.out.println(s1);//abfalsehello
        System.out.println(s1.length());//12

        s1.reverse();
        System.out.println(s1);//olleheslafba

        String s2 = s1.substring(1,3);
        System.out.println(s1);//不变
        System.out.println(s2);//子串




    }


    /*
    String, StringBuffer, StringBuilder类三者的区别
    String：不可变的字符序列，底层使用char[]或byte[]存储
    StringBuffer：可变的字符序列，线程安全（效率偏低）[里面都是同步方法]
    StringBuilder：可变的字符序列，线程不安全（效率较高）[JDK5.0新增]

    源码分析：
    String str = new String();//相当于char[] value = new char[0];
    String str1 = new String("abc");//相当于char[] value = new char[]{'a','b','c'};

    StringBuffer sb1 = new StringBuffer();//相当于char[] value = new char[16];底层创建了一个长度为16的数组
    System.out.println(sb2.length());//0
    sb1.append('a');//value[0] = 'a';
    sb1.append('b');//value[1] = 'b';//能连起来理解为什么python中append是添加元素到list的最后一个位置

    StringBuffer sb2 = new String("abc");//相当于char[] value = new char["abc".length + 16];

    //问题1. System.out.println(sb2.length());//3（如果是3+16，那length方法和StringBuffer可太难用了）
    //问题2. 扩容问题：如果要添加的数据底层数组盛不下了，那就需要扩容底层数组
            默认情况下，扩容为原来容量的2倍+2，同时将原有数组中的元素复制到新的数组中（源码）
    指导意义：开发中建议使用StringBuffer(int capacity)这个构造器造StringBuffer的对象，其中capacity就是需要的容量（数组长度）
            如果线程安全，还可以用StringBuilder(int capacity)


     */

    @Test
    public void test(){
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');
        System.out.println(sb1);//mbc，所以是可变的字符序列，上面方法根本不返回新对象了，直接改变原有字符串

        StringBuffer sb2 = new StringBuffer();
        System.out.println(sb2.length());//0

    }


}
