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
 * 5. HashMap的底层实现原理（JDK7中）
 * ① HashMap map = new HashMap();
 * --> 在实例化以后，底层创建了一个长度为16的一维数组，类型为Entry，即是说Entry[] table
 * (可能之后执行若干次put）
 * ② map.put(key1, value1);
 * 1> 首先调用key1所在类的hashCode()方法，计算出key的hash值；此hash值经过某种算法以后，得到entry1在Entry[]数组中的存放位置
 * 2> 如果此位置上的数据为null，key1-value1这个entry1则添加成功 --> 情况1
 *    如果此位置上的数据不为null，则此位置上已经存在一个或多个数据（以链表形式存放多个），则去比较key1和已经存在的一个或多个数据的hash值：
 *      如果key1的hash值与已经存在的数据hash值都不相同，则key1-value1添加成功； --> 情况2
 *      如果key1的hash值与已经存在的某一个数据key2-value2的hash值相同，则需要调用key1所在类的equals()方法，进行比较：
 *          如果equals()返回false，则entry1添加成功 --> 情况3
 *          如果equals()返回true，则使用value1替换掉value2（这点不同于理解！）
 *
 *    补充说明：关于情况2和情况3，此时key1-value1和原来的数据以链表的方式存储在一起（仍然七上八下针对JDK7&8)
 * ③ 在添加过程中，会不停地遇到扩容的问题。当超出临界值且添加的元素位置非null时（SC写的），则扩容-->默认扩容为原来容量的2倍，并将原有数据复制到新数组中
 *
 * 6.
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
