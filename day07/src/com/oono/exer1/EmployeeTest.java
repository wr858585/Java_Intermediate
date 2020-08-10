package com.oono.exer1;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
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

    //问题二：使用定制排序
    @Test
    public void test2(){

        Comparator comparator = new Comparator(){
            public int compare(Object o1, Object o2){
                if(o1 instanceof Employee && o2 instanceof Employee){
                    Employee e1 = (Employee)o1;
                    Employee e2 = (Employee)o2;

                    MyDate b1 = e1.getBirthday();
                    MyDate b2 = e2.getBirthday();

                    //为什么可以直接用三个return来写，因为一旦进入if，表明年数不同，则做差直接返回，若为正数b1大，若为负数b2大。return之后跳出当前结构。如果年一样，做差为0，进不去if，进入第二个if的判断，以此类推
                    //比较年
                    int yearGap = b1.getYear() - b2.getYear();
                    if(yearGap != 0){
                        return yearGap;
                    }

                    //比较月
                    int monthGap = b1.getMonth() - b2.getMonth();
                    if(monthGap != 0){
                        return monthGap;
                    }

                    //比较日
                    return b1.getDay() - b2.getDay();

                }
                throw new RuntimeException("输入的数据类型不一致");
            }
        };

        TreeSet set = new TreeSet(comparator);

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

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }


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
