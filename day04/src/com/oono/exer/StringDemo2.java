package com.oono.exer;

import org.junit.Test;

/**
 * @author oono
 * @date 2020 07 20
 */
public class StringDemo2 {

    /*
    获取两个字符串中最大相同字串
    如：str1 = "abcwerthelloyuiodef"，str2 = "cvhellobnm"
    提示：把短的那个串进行长度依次递减的字串与较长的串比较
     */

    public String getMaxSameString(String str1, String str2) {

        String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
        String minStr = (str1.length() < str2.length()) ? str1 : str2;

        int length = minStr.length();

        for (int i = 0; i < length; i++) {

            for (int x = 0, y = length - i; y <= length; x++, y++) {
                String subStr = minStr.substring(x, y);
                if (maxStr.contains(subStr)) {
                    return subStr;
                }
            }
        }
        return null;
    }

    @Test

    public void test() {
        System.out.println(getMaxSameString("cvhellobnm", "abcwerthelloyuiodef"));
    }
}

