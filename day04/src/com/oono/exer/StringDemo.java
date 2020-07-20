package com.oono.exer;

import org.junit.Test;

/**
 * @author oono
 * @date 2020 07 19
 */
public class StringDemo {

    /*
    将一个字符串的指定部分进行反转。
    比如："abcdefg" --> "abfedcg"
    分析：length = 7, start = 2, end = 5, value[2]和value[5]换，5 = 7 - 2，即arr[i]和arr[arr.length - i]换

    方式一：转换为char[]，用数组处理
     */

    public String reverse(int start, int end, String str){

        char[] arr = str.toCharArray();

        for (int i = start, j = end;i < j;i++,j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        String str1 = new String(arr);
        return str1;
    }

    //方式二：使用String的拼接操作
    public String reverse1(String str, int start, int end){
        //第一部分
        String reverseStr = str.substring(0,start);
        //第二部分
        for (int i = end; i >= start; i--) {
            reverseStr += str.charAt(i);
        }
        //第三部分
        reverseStr += str.substring(end + 1);
        return reverseStr;

    }

    //方式三：用StringBuffer/StringBuilder来替换方式二中的String，来优化效率
    public String reverse2(String str, int start, int end){
        StringBuilder builder = new StringBuilder(str.length());
        //第一部分
        builder.append(str.substring(0,start));
        //第二部分
        for (int i = end; i >= start; i--) {
            builder.append(str.charAt(i));
        }
        //第三部分
        builder.append(str.substring(end + 1));
        return builder.toString();
    }


    @Test
    public void testReverse(){
        String str = "abcdefg";
        String str1 = reverse(2,5,str);
        System.out.println(str1);
    }



}
