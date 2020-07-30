package com.oono.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Collection接口中声明的方法测试
 * <p>
 * 1. 必做事项：
 * 向Collection接口的实现类的对象中添加数据obj时，要求obj所在类重写equals()方法
 *
 * @author oono
 * @date 2020 07 30
 */
public class CollectionTest {

    @Test
    public void method1() {

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
//        boolean jerry = coll.add(new Person("Jerry", 20));
//        coll.add(jerry);
        coll.add(new Person("Jerry", 20));

        //contains(Object obj)：判断当前集合中是否包含obj
        //在判断是否包含obj时，会调用obj所在类的equals()方法

        boolean contains = coll.contains(123);
        System.out.println(contains);//true
        System.out.println(coll.contains(new String("Tom")));//true
        System.out.println(coll.contains(new Person("Jerry", 20)));//false-->true，Person类没有重写equals()方法返回false，现在写了值相等返回true

        //containsAll(Collection coll1)：判断coll集合中的所有元素是否都存在于当前集合中
        Collection coll1 = Arrays.asList(123, 456);
        System.out.println(coll.containsAll(coll1));//true

    }

    @Test
    public void test2() {

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //3. remove(Object obj)：从当前集合中删除obj元素
        System.out.println(coll.remove(123));//true，remove()方法有返回值，当移除成功时返回false
        System.out.println(coll);//[456, Person{name=Jerry, age=20}, Tom, false]
        coll.remove(new Person("Jerry", 20));
        System.out.println(coll);//[456, Tom, false]

        //4. removeAll(Collection coll1)：从当前集合coll中移除所有coll1的元素
        Collection coll1 = Arrays.asList(123, 456);
        coll.removeAll(coll1);
        System.out.println(coll);//[Tom, false]

    }

    @Test
    public void test3() {

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

/*
        //5. retainAll(Collection coll1)：只保留当前集合coll和coll1的交集
        Collection coll1 = Arrays.asList(123,456,789);
        coll.retainAll(coll1);
        System.out.println(coll);//[123, 456]
*/

        //6. equals(Object obj)：

        Collection coll1 = new ArrayList();
        coll1.add(123);
        coll1.add(456);
        coll1.add(new Person("Jerry", 20));
        coll1.add(new String("Tom"));
        coll1.add(false);

        System.out.println(coll.equals(coll1));//true

        Collection coll2 = new ArrayList();
        coll2.add(456);
        coll2.add(123);
        coll2.add(new Person("Jerry", 20));
        coll2.add(new String("Tom"));
        coll2.add(false);

        System.out.println(coll.equals(coll1));



    }
}
