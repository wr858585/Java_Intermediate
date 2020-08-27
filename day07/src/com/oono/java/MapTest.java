package com.oono.java;

import org.junit.Test;

import java.util.*;

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
 * 6. JKD8相较于JDK7在底层实现方面的不同：
 * ① new HashMap()在底层并没有创建一个长度为16的数组
 * ② JDK8底层的数组是Node[]而非Entry[]
 * ③ 首次调用put()方法时，底层创建长度为16的数组
 * ④ JDK7中HashMap底层结构只有数组+链表；JDK8中HashMap底层结构是数组+链表+红黑树
 *      --> 当数组上的某一索引位置上的元素以链表形式存储的数据个数>8个 且 当前数组的长度>64时，此时该索引上的所有数据全部用红黑树储存
 *
 * 7. Map中常用的方法
 * ① Map常用方法第一部分：增删改
 *     mtd1. Object put(Object key, Object value)：将指定的key-value添加到（或修改到）当前map中
 *     mtd2. void putAll(Map m)：将m中所有的key-value存放到map中
 *     mtd3. Object remove(Object key)：移除指定key的键值对，并返回其value
 *     mtd4. void clear()：清空当前map中的所有数据
 * ② Map常用方法第二部分：查询
 *     mtd5. Object get(Object key)：获取指定key对应的value
 *     mtd6. boolean containsKey(Object key)：判断是否包含指定key
 *     mtd7. boolean containsValue(Object value)：判断是否包含指定value
 *     mtd8. int size()：返回map中的键值对个数
 *     mtd9. boolean isEmpty()：判断当前map是否为空
 *     mtd10. boolean equals(Object obj)：判断当前map和参数对象obj是否相等
 * ③ Map常用方法第三部分：元试图操作
 *     mtd11. Set keySet()：返回所有key构成的set集合
 *     mtd12. Collection values()：返回所有value构成的Collection集合
 *     mtd13. Set entrySet()：返回所有key-value构成的Set集合
 * ④ 总结：
 * 增 put(Object key, Object value)
 * 删 remove(Object key)
 * 改 put(Object key, Object value)
 * 查 get(Object key)
 * 插入？--> 不存在的，因为Map是无序的，根本不存在从某个index上插入一个数值，直接添加即可
 * 长度 size()
 * 遍历 keySet(), values(), entrySet()或拼接keySet和values()
 *
 *
 * @author oono
 * @date 2020 08 15
 */
public class MapTest {

    /*
    Map常用方法第三部分：元视图操作
    mtd11. Set keySet()：返回所有key构成的set集合
    mtd12. Collection values()：返回所有value构成的Collection集合
    mtd13. Set entrySet()：返回所有key-value构成的Set集合
     */

    @Test
    public void test5(){

        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",45);

        //遍历所有的key集合
        Set set = map.keySet();
        //再用iterator遍历set
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for(Object obj : set){
            System.out.println(obj);
        }

        System.out.println("-------------");

        //遍历所有的value集合
        Collection values = map.values();
        for(Object obj : values){
            System.out.println(obj);
        }
        Iterator iterator1 = values.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("--------------");

        //遍历所有的entry集合

        //方式一：entrySet()[straightforward]
        Set entrySet = map.entrySet();
        Iterator iterator2 = entrySet.iterator();
        while(iterator2.hasNext()){
            Object obj = iterator2.next();
            //集合entrySet中的元素都是entry（key-value）
            Map.Entry entry = (Map.Entry) obj;
            //之后可以用getKey(),getValue()方法来获取对应的key/value
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println("-----------------");

        //方式二：运用get()方法[roundabout]
        Set keySet = map.keySet();
        Iterator iterator3 = keySet.iterator();
        while(iterator3.hasNext()){
            Object key = iterator3.next();
            Object value = map.get(key);
            System.out.println(key + "=" + value);
        }

    }

    /*
    Map常用方法第二部分：查询
    mtd5. Object get(Object key)：获取指定key对应的value
    mtd6. boolean containsKey(Object key)：判断是否包含指定key
    mtd7. boolean containsValue(Object value)：判断是否包含指定value
    mtd8. int size()：返回map中的键值对个数
    mtd9. boolean isEmpty()：判断当前map是否为空
    mtd10. boolean equals(Object obj)：判断当前map和参数对象obj是否相等
     */

    @Test
    public void test4(){

        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);

        //get(Object key)
        System.out.println(map.get(45));//123

        //containsKey(Object key)
        boolean isExist = map.containsKey("BB");
        System.out.println(isExist);//true

        //containsValue(Object value)
        boolean isExist1 = map.containsValue(123);
        System.out.println(isExist1);//true，找到一个就不会再找了，直接return true

        //isEmpty()

        map.clear();
        System.out.println(map.isEmpty());//true



    }

    /*
    Map常用方法第一部分：增删改
    mtd1. Object put(Object key, Object value)：将指定的key-value添加到（或修改到）当前map中
    mtd2. void putAll(Map m)：将m中所有的key-value存放到map中
    mtd3. Object remove(Object key)：移除指定key的键值对，并返回其value
    mtd4. void clear()：清空当前map中的所有数据
     */

    @Test
    public void test3(){
        //添加
        Map map = new HashMap();
        map.put("AA",123);
        map.put(45,123);
        map.put("BB",56);
        //（修改）上面key已经有"AA"，所以这里是替换操作（并非key值一样，所以这个进不去了！）
        map.put("AA",87);

        System.out.println(map);//{AA=87, BB=56, 45=123}

        Map map1 = new HashMap();
        map1.put("CC",123);
        map1.put("DD",123);

        map.putAll(map1);
        System.out.println(map);//{AA=87, BB=56, CC=123, DD=123, 45=123}

        //删除remove
        Object value = map.remove("CC");
        System.out.println(value);//123
        System.out.println(map);//{AA=87, BB=56, DD=123, 45=123}

        Object value1 = map.remove("CCCC");
        System.out.println(value);//null（没有"CCCC"这个key，所以返回的value也是null）

        //清空clear
        map.clear();//不是把map搞成null，而是0
        System.out.println(map.size());//0，表明没有元素了，但map并非null
        System.out.println(map);//{}

    }


    @Test
    public void test2() {

        Map map = new HashMap();

        map.put(123,"AA");
        map.put(456,"BB");
        map.put(12,"CC");

        System.out.println(map);

        Map map1 = new LinkedHashMap();//{456=BB, 123=AA, 12=CC}

        map1.put(123,"AA");
        map1.put(456,"BB");
        map1.put(12,"CC");

        System.out.println(map1);//{123=AA, 456=BB, 12=CC}



    }

    @Test
    public void test1(){

        HashMap map = new HashMap();

        map.put(null,null);



    }


}
