package com.oono.java;

import org.junit.Test;

/**
 * 其他常用类的使用
 * 1. System
 * 2. Math
 * 3. BigInteger和BigDecimal
 *
 * @author oono
 * @date 2020 07 23
 */
public class OtherClassesTest {

    @Test
    public void test(){

        String javaVersion = System.getProperty("java.version");
        System.out.println(javaVersion);//1.8.0_131


        String javaHome = System.getProperty("java.home");
        System.out.println(javaHome);//C:\dev\developer_tools\java\jdk1.8.0_131\jre


        String osName = System.getProperty("os.name");
        System.out.println(osName);//Windows 10


        String osVersion = System.getProperty("os.version");
        System.out.println(osVersion);//10.0


        String userName = System.getProperty("user.name");
        System.out.println(userName);//oono


        String userHome = System.getProperty("user.home");
        System.out.println(userHome);//C:\Users\oono


        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);//C:\dev\workspace\workspace_idea1\Java_Intermediate\day04

    }





}
