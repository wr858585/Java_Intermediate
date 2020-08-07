package com.oono.java1;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 1. Set接口的框架结构
 *
 * |--Collection接口：单列集合，用来存储一个一个的对象
 *      |--Set接口：存储无序的、不可重复的数据
 *          |--HashSet：作为Set接口的主要实现类；线程不安全；可以存储null值
 *              |--LinkedHashSet：是HashSet的子类；遍历其内部数据时，可以按照添加顺序去遍历
 *          |--TreeSet：可以按照添加的对象的指定属性，进行排序（红黑树）
 *
 * 2. Set接口中没有额外定义新的方法，所以只有Collection接口中提供的方法
 *
 * 3. 要求向Set中添加的元素，其所在的类一定要重写两个方法：hashCode(), equals()
 * 4. 要求重写的hashCode()和equals()要尽可能保持一致性 --> 相等的对象必须具有相等的散列码/哈希值
 *      --> 如何保持这种一致性：对象中用作比较equals()的属性，都应该用来计算hashCode值
 *
 * @author oono
 * @date 2020 08 07
 */
public class SetTest {

    /*
    一、Set：存储无序的、不可重复的数据（以HashSet为例）
    1. 无序性：
    ① 不等于随机性，即是说顺序是确定的
    ② 存储的数据在底层数组中并非按照index这个顺序来存储/添加的，而是根据数据的Hash值决定放在数组的什么位置
    2. 不可重复性：
    ① 保证添加的元素按照equals()方法判断时，不能返回true；即相同的元素只能添加一个

    二 、Set中添加元素的过程（以HashSet为例）
    1. 我们向HashSet中添加元素a，首先调用a所在类的hashCode()方法，来计算a的哈希值
    2. 此哈希值通过某种算法，计算出在HashSet底层数组中的存放位置index
    3. 判断数组此位置上是否已经有元素：
        ① 如果此位置上没有其他元素，目前为null，则a直接添加成功 -->情况1
        ② 如果此位置上有其他元素b，或者已经存在以链表形式存在的多个元素b，c，d；
            1> 首先比较a与b的哈希值：如果不相同，则a添加成功 -->情况2
            2> 如果哈希值相同，再进而调用元素a所在类的equals方法，a.equals(b)，a.equals(c)，a.equals(d)：
                如果返回true，则添加失败；
                如果全返回false，则a添加成功 -->情况3

    说明：
    1.对于添加成功的情况2和情况3而言，元素a与已经存在指定索引位置上的数据，以链表的方式进行存储
        ① JDK7中，把a放到数组中，指向原来的元素b，c，d
        ② JDK8中，原来的元素b仍然在数组中不动，c，d，a以链表的形式指向b
    2. 总结：HashSet底层结构较为复杂，为数组+链表；对于插入元素JDK不同版本的演化，助记：7上8下



     */

    @Test
    public void test1(){

        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }


}
