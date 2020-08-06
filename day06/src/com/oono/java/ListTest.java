package com.oono.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * |--Collection接口：单列集合，用来存储一个一个的对象
 *      |--List接口：存储有序的、可重复的数据 --> “动态”数组
 *          |--ArrayList：作为List接口的主要实现类（since JDK1.2)。线程不安全，效率高；底层结构仍然用Object[]存储：Object[] elementData
 *          |--LinkedList：（since JKD1.2)。底层使用的是双向链表存储 --> 对于频繁的插入和删除操作，使用LinkedList比ArrayList效率高
 *          |--Vector：作为List接口的古老实现类 -- JDK1.0就有了（接口是JDK1.2才有的）。线程不安全，效率低；底层结构仍让用Object[]存储：Object[] elementData
 *          （回想：String/StringBuffer等为char[]或者byte[] value）
 *
 * ArrayList源码分析
 * 1. JDK7的情况
 * ① ArrayList list = new ArrayList();//底层创建了一个长度是10的Object[]数组，elementData
 * ② list.add(123);//elementData[0] = new Integer(123);
 *      ...
 *   list.add(11);//如果此次的添加导致底层elementData数组容量不够了 --> 扩容
 * 默认情况下，扩容为原来容量的1.5倍；同时将原有数组中的数据复制到新的数组中，作为elementData的新的对象
 * ③ 结论：建议开发中去使用带参的构造器ArrayList list = new ArrayList(int capacity);这样可以避免用空参构造器是长度是10的底层数组，之后一直扩容出现效率不高的问题
 * 2. JDK8的情况
 * ① ArrayList list = new ArrayList();//底层Object[] elementData初始化为{}，意味着并没有创建长度为10的数组（相当于懒汉式，节省了内存空间）
 * ② list.add(123);//第一次调用添加操作时，底层才创建长度为10的数组，并把数据123添加到elementData[0]上
 *      ...
 *    后续的添加和扩容操作，与JDK7无异
 * 3.总结
 * JDK7中ArrayList的实例化，类似于单例模式中的饿汉式；JDK8中ArrayList的实例化，类似于懒汉式，延迟了数组的创建，节省内存
 *
 * LinkedList源码分析
 * ① LinkedList list = new LinkedList();//内部声明了Node类型的first和last属性，默认值为null
 * ② list.add(123);//将123封装到Node中，创建了Node的对象
 *      其中Node定义为：（一个内部类）体现了LinkedList双向链表的数据结构
 *      private static class Node<E>{
 *          E item;
 *          Node<E> next;
 *          Node<E> prev;
 *
 *          Node(Node<E> prev, E element, Node<E> next){
 *          this.item = element;
 *          this.next = next;
 *          this.prev = prev;
 *          }
 *      }
 *
 * Vector源码分析
 * ① 通过构造器Vector()实例化时，创造长度为10的数组
 * ② 扩容默认为原来数组的2倍
 *
 * 面试题：比较ArrayList, LinkedList, Vector三者异同
 * 同：
 * 三者都是List接口的实现类，所以都是存储有序的、可重复的数据
 * 异：
 * 见上面
 *
 * List接口中的常用方法
 * 1.
 *
 *
 * @author oono
 * @date 2020 08 02
 */
public class ListTest {

    /*

     */

    @Test
    public void test2(){

        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom", 12));
        list.add(456);

        //mtd4. int indexOf(Object obj)：返回obj在集合中首次出现的索引；如果不存在，返回-1
        int index = list.indexOf(456);
        System.out.println(index);//1

        //mtd5. int lastIndexOf(Object obj)：返回obj在集合中末次出现的索引
        System.out.println(list.lastIndexOf(456));//4

        //mtd6. Object remove(int index)：移除指定index位置的元素，并返回此元素



    }

    @Test
    public void test1(){

        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom", 12));
        list.add(456);

        System.out.println(list);//[123, 456, AA, Person{name=Tom, age=12}, 456]

        //mtd1. void add(int index, Object ele)：在指定index位置插入元素ele
        list.add(1,"BB");
        System.out.println(list);//[123, BB, 456, AA, Person{name=Tom, age=12}, 456]

        //mtd2. boolean addAll(int index, Collection eles)：从指定index开始将ele中的所有元素添加进来
        List list1 = Arrays.asList(1, 2, 3);
//        list.add(list1);这个相当于是把list1这个ArrayList整体加到list中了，因为可以加Object类的任意数据，和addAll加list1中的所有元素有本质区别
        list.addAll(list1);
        System.out.println(list.size());//9

        //mtd3. Object get(int index)：获取指定index位置的元素
        System.out.println(list.get(0));//123

    }

}
