package com.oono.java;

import org.junit.Test;

import java.util.HashMap;

/**
 * 1. Map（接口）框架结构
 *      |--Map：双列数据，用于存储具有key-value键值对的数据（类似于高中的“函数”）
 *          |--HashMap：作为Map接口的主要实现类（JDK1.2）；线程不安全，效率高；可以存储值为null的key/value的
 *              |--LinkedHashMap：保证在遍历map元素时，可以按照添加的顺序实现遍历
 *                      mechanism：在原有的HashMap底层基础上，添加了一对指针，指向前一个和后一个元素
 *                      使用场景：对于频繁的遍历操作，LinkedHashMap的效率会高于HashMap
 *          |--TreeMap：保证按照添加的key-value进行排序，实现排序遍历。此时考虑key的自然排序/定制排序（而非value的排序）
 *              底层使用的是：红黑树
 *          |--Hashtable：作为Map接口的古老实现类（JDK1.0）；线程安全，效率低；不能存储值为null的key/value
 *              |--Properties：常用来处理配置文件。key和value都是String类
 *
 * 2. HashMap的底层结构：数组+链表（JDK7以及以前）
 *                      数组+链表+红黑树（JDK8及其以后）
 *
 * 3. 面试题
 * ① HashMap的底层实现原理（主流题目）
 * ② HashMap和Hashtable的异同（较老）
 * ③ CurrentHashMap与Hashtable的异同
 *
 * 4. Map结构的理解：
 * ① Map中的key：无序的、不可重复的 --> 使用set存储所有key [以hashMap为例：要求key所在（自定义）类要重写equals()和hashCode()；以TreeMap为例：实现Comparable/Comparator接口]
 * ② Map中的value：无序的、可重复的 --> 使用Collection来存储（不满足Set/List的存储条件）
 * [value所在类要重写equals()，不用重写hashCode()，因为hashCode()其实是Set用来提高查找set中位置的效率的一个方法，而比较两个元素是否相同本质上之后仍然是用equals()比较；
 * 这里，因为entry已经通过key的hashCode()+equals()方法快速定位了要找的是哪一个entry，自然其属性value也就马上出来了，所以无需再用hashCode()定位，直接equals()比较即可]
 * ③ 一个键值对：key-value构成一个entry对象，Map中的entry也是存储的一个无序的、不可重复的 --> 使用Set存储
 *
 * 5.
 *
 * @author oono
 * @date 2020 08 15
 */
public class MapTest {

    @Test
    public void test1(){

        HashMap map = new HashMap();

        map.put(null,null);



    }


}
