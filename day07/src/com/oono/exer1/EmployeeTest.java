package com.oono.exer1;

import org.junit.Test;

import java.util.TreeSet;

/**
 * 创建5个Employee的对象，并把这些对象放到TreeSet集合中（下一章：TreeSet需要使用泛型来定义）
 * 遍历集合，按照下面两种排序方式：
 * 1. 使Employee实现Comparable接口，并按name排序
 * 2. 创建TreeSet时实现Comparator接口，按照生日日期的先后顺序排序
 *
 *
 * @author oono
 * @date 2020 08 08
 */
public class EmployeeTest {

    //问题一：使用自然排序
    @Test
    public void test1(){

        TreeSet set = new TreeSet();

        Employee e1 = new Employee("刘德华",55,new MyDate(1965,5,4));
        Employee e2 = new Employee("张学友",43,new MyDate(1987,5,4));
        Employee e3 = new Employee("郭富城",44,new MyDate(1987,5,9));
        Employee e4 = new Employee("黎明",51,new MyDate(1954,8,12));
        Employee e5 = new Employee("梁朝伟",21,new MyDate(1978,12,4));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        for(Object obj : set){
            System.out.println(obj);
        }

/*
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            iterator.next();
        }
*/


    }


}
