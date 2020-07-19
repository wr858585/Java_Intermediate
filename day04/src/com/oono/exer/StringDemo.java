package com.oono.exer;

/**
 *
 *
 *
 * @author oono
 * @date 2020 07 19
 */
public class StringDemo {

    /*
    将一个字符串的指定部分进行反转。
    比如："abcdefg" --> "abfedcg"
    分析：length = 7, start = 2, end = 6（左闭右开）, value[2]和value[5]换，5 = 6 - 2 + 1，所以i和end - i + 1换

    方式一：
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

    public static void main(String[] args) {
        StringDemo test = new StringDemo();
        System.out.println(test.reverse(2,5,"abcdefg"));
    }




}
