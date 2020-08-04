package com.oono.java;

/**
 * |--Collection接口：单列集合，用来存储一个一个的对象
 *      |--List接口：存储有序的、可重复的数据 --> “动态”数组
 *          |--ArrayList：作为List接口的主要实现类（since JDK1.2)。线程不安全，效率高；底层结构仍然用Object[]存储：Object[] elementData
 *          |--LinkedList：（since JKD1.2)。底层使用的是双向链表存储 --> 对于频繁的插入和删除操作，使用LinkedList比ArrayList效率高
 *          |--Vector：作为List接口的古老实现类 -- JDK1.0就有了（接口是JDK1.2才有的）。线程不安全，效率低；底层结构仍让用Object[]存储：Object[] elementData
 *          （回想：String/StringBuffer等为char[]或者byte[] value）
 *
 * 面试题：比较ArrayList, LinkedList, Vector三者异同
 * 同：
 * 三者都是List接口的实现类，所以都是存储有序的、可重复的数据
 * 异：
 * 见上面
 *
 * @author oono
 * @date 2020 08 02
 */
public class ListTest {

}
