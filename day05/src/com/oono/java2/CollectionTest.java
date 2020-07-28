package com.oono.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 一、集合框架的概述
 *
 * 1. 集合、数组，都是对多个数据进行存储操作的结构 --> java容器
 *      说明：此时的存储，主要指的是内存层面的临时存储，不涉及到硬盘层面的持久化存储（.txt,.jpg,.avi，甚至在数据库中，都是持久化的存储）
 *
 * 2.1 数组在存储多个数据方面的特点：
 * ① 一旦初始化后，长度就确定了
 * ② 一旦定义好后，数组的元素类型就确定了
 * 2.2 数组在存储多个数据方面的缺点：
 * ① 一旦初始化后，长度不可修改
 * ② 提供的方法非常有限，尤其对添加、删除、插入元素非常不便，同时效率不高（数据结构，如插入的方式不好；而集合中的数据结构就比较丰富了，如链表的插入方式效率较高）
 * ③ 获取数组中实际的元素个数的需求，数组没有对应的属性或方法，需要自己写total来记录（比如arr长度为5，两个null，三个元素，没有直接的办法获取到当前数组的实际元素的个数）
 * ④ 数组存储数据的特点：有序、可重复。对于无序的需求，或者不可重复的需求，数组是不能满足的
 *
 * 二、集合框架
 *      |-- Collection接口：单列集合，用来存储一个一个的对象（基本数类用包装类转化成对象也可以存）
 *          |--List接口：存储有序的、可重复的数据 --> 又叫"动态"数组
 *              |--ArrayList, LinkedList, Vector
 *          |--Set接口：存储无序的、不可重复的数据 --> 高中讲的"集合"
 *              |--HashSet, LinkedHashSet, TreeSet
 *      |-- Map接口：双列集合，用来存储一对一对的数据（key-value） --> 高中讲的“函数”
 *              |--HashMap, LinkedHashMap, TreeMap, HashTable, Properties
 *
 * 三、Collection接口中的方法的使用
 * 1.
 *
 * @author oono
 * @date 2020 07 27
 */
public class CollectionTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();//多态；Collection是接口不能造对象

        //add(Object e)：将元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱成包装类，也是obj
        coll.add(new Date());

        //size()：获取添加的元素的个数
        System.out.println(coll.size());//4

        //addAll(Collection coll1)：将coll1集合中的元素添加到当前的集合中
        Collection coll1 = new ArrayList();//多态；Collection是接口不能造对象
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);

        System.out.println(coll.size());//6
        System.out.println(coll);//[AA, BB, 123, Tue Jul 28 17:03:30 CST 2020, 456, CC]

        //clear()：清空集合元素
        coll.clear();

        //isEmpty()：判断当前集合是否为空
        System.out.println(coll.isEmpty());//true


    }


}
