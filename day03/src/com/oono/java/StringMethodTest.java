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
    String的常用方法：第三波（替换，匹配，切片）[split很常用]
    替换：
    1. String replace(char oldChar, char newChar)：返回一个新的字符串，用newChar替换oldChar（char型，只找一个字符）
    2. String replace(CharSequence target, CharSequence replacement)：返回一个新的字符串，用指定的字面量replacement替换原有的字面量target（char[]型，可以替换字符串）
    3. String replaceAll(String regex, String replacement)：使用给定的placement替换字符串中的所有匹配给定的正则表示式的子字符串（之后web再讲）
    4. String replaceFirst(String regex, String replacement)：替换此字符串匹配给定的正则表达式的第一个子字符串
    匹配：
    5. boolean matches(String regex)：告知此字符串是否匹配给定的正则表达式
    切片：
    6. String[] split(String regex)：根据给定正则表示式的匹配，来拆分字符串，存到一个String型数组中
    7. String[] split(String regex, int limit)：同上，最多不超过limit个元素；如果超过，则最后一个元素全部存剩余的String



     */

    @Test
    public void test4(){

        String str1 = "北北京尚硅谷教育北京";
        String str2 = str1.replace("北", "东");
        System.out.println(str1);//北北京尚硅谷教育北京
        System.out.println(str2);//东东京尚硅谷教育东京

        String str3 = str1.replace("北京", "上海");
        System.out.println(str3);//北上海尚硅谷教育上海

        System.out.println("*************************");

        String str = "12hello34world5392java92931mysql2312";
        System.out.println(str.replaceAll("\\d+", ","));//,hello,world,java,mysql,

        str = "12345";
        //判断str字符串是否全部由数字组成，即有1-n个数字组成
        boolean matches = str.matches("\\d+");
        System.out.println(matches);
        String tel = "0571-4534289";
        //判断这是否是一个杭州的固定电话
        boolean result = tel.matches("0571-\\d{7,8}");
        System.out.println(result);

        System.out.println("*************************");

        str = "hello|world|java";
        String[] strs = str.split("\\|");
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
        System.out.println();
        str2 = "hello.world.java";
        String[] strs2 = str2.split("\\.");
        for (int i = 0; i < strs2.length; i++) {
            System.out.println(strs2[i]);
        }



    }


    /*
    String的常用方法：第二波（查找）[contains很常用]
    1. boolean endsWith(String suffix)：测试此字符串是否以指定的后缀结束
    2. boolean startsWith(String prefix)：测试此字符串是否以指定的前缀开始
    3. boolean startsWith(String prefix, int toffest)：从指定索引开始，测试此字符串是否以指定的前缀开始
    4. boolean contains(CharSequence s)：当且仅当此字符串包含指定的char值序列时，返回true（常用）
    5. int indexOf(String str)：返回指定字符串第一次出现处的索引
    6. int indexOf(String str, int fromIndex)：从指定索引开始，返回指定字符串第一次出现的索引
    7. int lastIndexOf(String str)：返回指定字符串最后一次出现的索引
    8. int lastIndexOf(String str, int from Index)：从指定索引开始[反向搜索]，返回指定字符串最后一次出现的索引

    注意点：
    1. indexOf()和lastIndexOf()的返回值都是-1，如果未找到
    2. 什么情况下，indexOf()和lastIndexOf()的返回值相同？
    --> 情况1：存在唯一的一个str
    --> 情况2：不存在str（都返回-1）

     */

    @Test
    public void test3(){

        String str1 = "helloworld";
        boolean b1 = str1.endsWith("ld");//true，后缀可以是字符串
        boolean b2 = str1.startsWith("Hel");//false，区分大小写
        boolean b3 = str1.startsWith("ll",2);//true
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

        String str2 = "wo";
        System.out.println(str1.contains(str2));//true

        System.out.println(str1.indexOf("lo"));//3
        System.out.println(str1.indexOf("lol"));//-1（算是一个规定，Java中很多没找到的都源码写返回-1）
        System.out.println(str1.indexOf("lo", 5));//-1

        String str3 = "hellorworld";
        System.out.println(str3.lastIndexOf("or"));//7
        System.out.println(str3.lastIndexOf("or", 6));//4



    }


    /*
    String的常用方法：第一波（常规）[很常用]
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
