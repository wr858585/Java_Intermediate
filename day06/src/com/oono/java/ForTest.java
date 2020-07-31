package com.oono.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JDK5.0 新增的foreach循环，用于遍历集合和数组
 *
 *
 *
 *
 * @author oono
 * @date 2020 07 31
 */
public class ForTest {

    @Test
    public void test1(){

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //for(集合元素的数类 局部变量 : 集合对象){}
        //内部仍然用的迭代器其实source code
        for(Object obj : coll){
            System.out.println(obj);
        }

    }

    @Test
    public void test2(){

        int arr[] = new int[]{1,2,3,4,5,6};

        //for(数组元素的数类 局部变量 : 数组对象){}
        for(int i : arr){
            System.out.println(i);
        }

    }

    @Test
    //笔试题
    public void test3(){

        String[] arr = new String[]{"MM","MM","MM"};

        for(int i = 0; i < arr.length; i++){
            arr[i] = "GG";
        }
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        //





    }

}
