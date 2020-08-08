package com.oono.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author oono
 * @date 2020 08 08
 */
public class forEachTest {

    @Test
    public void test1(){

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(343);
        coll.add(343);

        coll.forEach(System.out::println);//Java8新特性λ表达式

    }


}
