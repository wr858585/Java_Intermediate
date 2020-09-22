package com.oono.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 关于自定义泛型类/泛型接口（见Order类）
 * @author oono
 * @date 2020 09 01
 */
public class OrderTest {

    @Test
    public void test1(){

        //如果定义了泛型类，实例化没有指明泛型的类型，则认为是Object类（不建议）
        //所以，要求：如果定义了一个带泛型的类，则在实例化时一定要指明该泛型的类型是什么
        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("ABC");

        //建议：实例化时指明泛型的类型
        Order<String> order1 = new Order<>("A公司订单",001,"苹果订单");

        order1.setOrderT("枣子订单");
    }

    @Test
    public void test2(){

        SubOrder sub = new SubOrder();

        //SubOrder类继承的时候如果写明了<Integer>，则将SubOrder所带的泛型的类型指明为Integer，调用Order类中的结构带的泛型也都是Integer类了
        sub.setOrderT(12);

        SubOrder1 sub1 = new SubOrder1();

        //SubOrder1类继承的时候也没有指明泛型是什么，所以实例化的时候要指明，否则就像这里sub1一样可以传任意东西
        sub1.setOrderT(new Object());

        SubOrder1<String> sub2 = new SubOrder1<>();
        sub2.setOrderT("大西瓜");
    }

    @Test
    public void test3(){


        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = null;

        //泛型不同的引用不能相互赋值
//        list1 = list2;这是不行的

    }

    //测试泛型方法
    @Test
    public void test4(){

        Order<String> order = new Order();
        Integer[] arr = new Integer[]{1,2,3,4};
        //泛型方法在调用时，指明泛型参数的类型
        List<Integer> integers = order.copyFromArrayToList(arr);

        System.out.println(integers);//[1, 2, 3, 4]




    }
}
