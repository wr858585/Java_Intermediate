package com.oono.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍历：使用Iterator接口
 * 1. 内部的方法：hasNext() & next()方法
 * 2. 集合对象coll每次调用iterator()方法，都会生成一个全新的迭代器对象，默认游标都在集合的第一个元素之前
 * 3. 内部定义了remove()方法，可以在遍历的时候删除集合中的元素。此方法不同于集合直接调用remove()，为两个类的方法
 *
 *
 * @author oono
 * @date 2020 07 31
 */
public class IteratorTest {

    @Test
    public void test1(){

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        Iterator iterator = coll.iterator();

        /*方式一：（演示，开发中不用）
        System.out.println(iterator.next());//123
        System.out.println(iterator.next());//456
        System.out.println(iterator.next());//Person{name=Jerry, age=20}
        System.out.println(iterator.next());//Tom
        System.out.println(iterator.next());//false
        //报异常：NoSuchElementException
        System.out.println(iterator.next());//NoSuchElementException
*/

        //方式二：开发中也不这么写，用方式三
        for (int i = 0; i < coll.size(); i++) {
            System.out.println(iterator.next());
        }

        //方式三：recommended
        while(iterator.hasNext()){
            //next()：①指针下移 ②将下移以后的集合位置上的元素返回
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        Iterator iterator = coll.iterator();

        //错误方式一：多次指针下移
        /*while(iterator.next() != null){
            System.out.println(iterator.next());
        }*/
        //456 -> Tom -> NoSuchElementException（因为每次next()都会首先指针下移）

        //错误方式二：死循环
        while(coll.iterator().hasNext()){
            System.out.println(coll.iterator().next());
        }//123 123 123...

    }

    @Test
    public void test3(){

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //删除"Tom"
        Iterator iterator = coll.iterator();

        while(iterator.hasNext()){
            Object obj = iterator.next();
            if("Tom".equals(obj)){
                iterator.remove();
            }
        }

        //遍历删除后的集合
        Iterator iterator1 = coll.iterator();//必须要重新生成一个迭代器iterator1，否则如果用原来的iterator默认游标已经在最后一个元素上了，不会sout出任何东西
//      或者给iterator重新赋值，成一个新的迭代器也行：iterator = coll.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

    }




}
