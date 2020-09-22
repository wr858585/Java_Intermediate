package com.oono.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections：操作List,Set,Map的工具类（注意：Collection接口只包含List和Set接口，存储单列数据；而Map接口是并列于Collection接口的结构，存储双列数据。但是Collections都可以操作）
 *
 * 面试题：Collection和Collections的区别
 * ① Collection是存储单列数据的接口
 * ② Collections是操作Collection和Map的工具类
 *
 *
 * @author oono
 * @date 2020 08 27
 */
public class CollectionsTest {

    /*
    reverse(List)：反转List中元素的顺序
    shuffle(List)：对List中的元素进行随机排序
    sort(List)：根据元素的自然顺序对指定List集合元素进行升序排列
    sort(List, Comparator)：根据指定的Comparator产生的顺序对List集合元素进行排序
    swap(List, int, int)：将指定的list集合中的两个index的元素互换位置

    Object max(Collection)：根据元素的自然排序，返回给定的集合中的最大元素
    Object max(Collection, Comparator)：根据Comparator指定的顺序，返回给定的集合中的最大元素
    Object min(Collection)
    Object min(Collection, Comparator)
    int frequency(Collection, Object)：返回集合中指定元素出现的次数
    void copy(List dest, List src)：将src的list复制，创建一个新的list dest，复制过去
    boolean replaceAll(List list, Object oldVal, Object newVal)：使用新的value替换旧的value
     */

    @Test
    public void test1(){

        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);

        List dest = new ArrayList();

        Collections.copy(dest, list);

        System.out.println(dest);//

        /*
        Collections工具类提供了多个snychronizedXxx()方法用于解决ArrayList,HashMap等线程不安全的问题
        （而不是去用古老实现类Vector,HashTable）
        这些方法可以将指定集合包装成线程同步的集合，从而可以解决：多线程并发访问集合时的线程安全问题
         */

        //返回的list1就成了线程安全的
        List list1 = Collections.synchronizedList(list);


    }

    @Test
    public void test(){

        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(-97);
        list.add(0);

        System.out.println(list);//[123, 43, 765, -97, 0]

        Collections.reverse(list);
        System.out.println(list);//[0, -97, 765, 43, 123]

        Collections.shuffle(list);
        System.out.println(list);//[-97, 0, 123, 765, 43]

        Collections.sort(list);
        System.out.println(list);//[-97, 0, 43, 123, 765]

        Collections.swap(list, 1, 2);
        System.out.println(list);//[-97, 43, 0, 123, 765]

        Object max = Collections.max(list);
        System.out.println(max);//765
        Object min = Collections.min(list);
        System.out.println(min);//-97

        Object frequency = Collections.frequency(list, 765);
        System.out.println(frequency);//1

    }

}
