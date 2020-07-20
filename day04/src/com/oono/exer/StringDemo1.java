package com.oono.exer;

import org.junit.Test;

/**
 * @author oono
 * @date 2020 07 20
 */
public class StringDemo1 {

    /*
    获取一个字符串在另一个字符串中出现的次数
    例如："ab"在"abkkcadkabkebfkabkskab"中的次数
     */

    /**
     * 获取subStr在mainStr中出现的次数
     *
     * @param mainStr
     * @param subStr
     * @return
     */
    public int getCount(String mainStr, String subStr) {
        int count = 0;
        for (int i = mainStr.indexOf(subStr); i <= mainStr.length() - subStr.length(); i++) {
            if (subStr.charAt(0) == mainStr.charAt(0)) {
                String compareStr = mainStr.substring(i, subStr.length() + i);
                if (compareStr.equals(subStr)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getCount1(String mainStr, String subStr) {
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int index = 0;
        if (mainLength >= subLength) {

/*  方式一：
            while ((index = mainStr.indexOf(subStr)) != -1) {
                count++;
                mainStr = mainStr.substring(index + subStr.length());
            }
*/
//  方式二：
            while((index = mainStr.indexOf(subStr,index)) != -1){
                count++;
                index += subLength;
            }

            return count;
        }
        return 0;
    }

    @Test
    public void testGetCount() {
        String str1 = "abkkcadkabkebfkabkskab";
        String str2 = "ab";
        System.out.println(getCount(str1, str2));
    }

    @Test
    public void testGetCount1(){
        String str1 = "abkkcadkabkebfkabkskab";
        String str2 = "ab";
        System.out.println(getCount1(str1, str2));
    }


}
