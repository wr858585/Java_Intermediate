package com.oono.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型类
 *
 * @author oono
 * @date 2020 09 01
 */
public class Order<T> {

    String orderName;
    int orderId;

    //类的内部结构就可以使用类的泛型

    T orderT;

    public Order() {
        //编译不通过
//        T[] arr = new T[10];

    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }


    //    }*/
//        System.out.println(orderT);
//    /*public static void show(T orderT){
//static方法不能使用任何带泛型的结构，因为带泛型的结构是实例化层面上的，static是类层面上的，加载要在于实例，所以不行
    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

//以上方法都不是泛型方法，必须要在方法中出现泛型的结构
//换句话说，泛型方法所属的类是不是带泛型的类，都可以，重点是前面有泛型结构


    //这是一个泛型方法with泛型结构<E>置于返回值类型之前
    //泛型方法是可以static的，因为泛型参数是在调用方法时确定的，并非在实例化时确定的
    //intuition：泛型去修饰方法的目的是，返回的数据类型和传入方法的形参的数据类型相同。基于这个需求，而且高复用性，所以有了泛型方法。
    public <E> List<E> copyFromArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();

        for (E elements : arr){
            list.add(elements);
        }

        return list;
    }

}

