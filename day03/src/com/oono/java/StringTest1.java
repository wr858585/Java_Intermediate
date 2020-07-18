package com.oono.java;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * 涉及到String类与其他结构的转换
 *
 *
 * @author oono
 * @date 2020 07 18
 */
public class StringTest1 {

    /*
    String与byte[]之间的转换
    String --> byte[]：调用String的getBytes()方法（编码）
    byte[] --> String：（解码）
    编码：字符串 --> 字节（把看得懂的内容，转换为计算机底层的二级制数据）
    解码：字节 --> 字符串（把看不懂的字节，转换为看得懂的东西）
    说明：解码时，要求解码使用的字符集character set（解码集）要同编码使用的字符集（编码集）一致，否则会出现乱码
     */

    @Test
    public void test2() throws UnsupportedEncodingException {

        String str1 = "abc123中国";
        byte[] arr = str1.getBytes();//使用默认的字符集，进行编码
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        byte arr1[] = str1.getBytes("gbk");//使用gbk字符集进行编码（之后IO流细讲）
        System.out.println(arr1);

        System.out.println("****************************");

        String str2 = new String(arr);//使用默认的字符集，进行解码
        System.out.println(str2);//abc123中国，编码和解码的字符集是一样

        String str3 = new String(arr1);
        System.out.println(str3);//abc123�й�，编码和解码的字符集是不同

        String str4 = new String(arr1,"gbk");
        System.out.println(str4);//abc123中国


    }


    /*
    String与char[]之间的转换
    String --> char[]：调用String的toCharArray()方法
    char[] --> String：调用String的构造器
     */

    @Test
    public void test1(){
        String str1 = "abc123";
        char[] charArray = str1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }

        char arr[] = new char[]{'h','e','l','l','o'};
        String str2 = new String(arr);
        System.out.println(str2);

    }


    /*
    String与基本数类，和包装类之间的转换（复习）
    String --> 基本数类和包装类：调用包装类的静态方法parseXxx(String str)。不能用强转符强转，因为不存在子父类关系不能这样操作
    基本数类和包装类 --> String：调用String重载的valueOf(xxx)方法。或者也可以用连接符+后面拼一个字符串，如 + "";
     */

    @Test
    public void test() {
        String str1 = "123";
        int num = Integer.parseInt(str1);//123
        String str2 = String.valueOf(num);//"123"
        String str3 = num + "";//"123"

        System.out.println(str1 == str3);//false，因为只要有一个是变量，则最终拼接结果存放在堆中（只有全部为常量的拼接，结果才在常量池中；或者调用intern()方法）
    }
}
