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
/*
        //方式一：普通for循环 --> 赋值操作成功，arr改成了{"GG","GG","GG"}
        for(int i = 0; i < arr.length; i++){
            arr[i] = "GG";
        }
*/

        //方式二：增强for循环 --> 赋值操作失败，arr仍然为{"MM","MM","MM"}，因为
        for(String str : arr){
            str = "GG";
        }

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }





    }

}
