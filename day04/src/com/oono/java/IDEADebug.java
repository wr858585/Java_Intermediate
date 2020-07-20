package com.oono.java;

import org.junit.Test;

/**
 * @author oono
 * @date 2020 07 20
 */
public class IDEADebug {

    @Test
    public void testStringBuffer(){

        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);//

        System.out.println(sb.length());//

        System.out.println(sb);//

        StringBuffer sb1 = new StringBuffer(str);//
        System.out.println(sb1);//

    }

}
