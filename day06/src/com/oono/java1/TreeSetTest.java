package com.oono.java1;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author oono
 * @date 2020 08 07
 */
public class TreeSetTest {

    /*
    1. 向TreeSet中添加数据，要求元素为同一个类的对象（这样才有对应的属性可以比较大小，进行排序；否则报ClassCastException）
    2. 两种排序方式：自然排序（实现Comparable接口）和定制排序
    3. 自然排序中，比较两个对象相同的标准为compareTo()方法return 0，而不再之前HashSet中用的是equals()方法
            --> 其实TreeSet和TreeMap底层都是用的红黑树存储数据的，这种数据结构是不会存相同数据的
    4. 定制排序中，比较两个对象相同的标准为compare()方法return 0，而不是equals()来判断
     */

    @Test
    public void test1(){

        TreeSet treeSet = new TreeSet();

/*  报错（因为元素数类不一致）：ClassCastException
        treeSet.add(123);
        treeSet.add(456);
        treeSet.add("AA");
        treeSet.add(new User("Tom",12));
*/

/*  举例一：整形 --> 遍历时会从小到大排序
        treeSet.add(34);
        treeSet.add(-34);
        treeSet.add(43);
        treeSet.add(11);
        treeSet.add(8);
*/
        treeSet.add(new User("Tom",12));
        treeSet.add(new User("Jerry",32));
        treeSet.add(new User("Jim",2));
        treeSet.add(new User("Mike",65));
        treeSet.add(new User("Jack",33));
        treeSet.add(new User("Jack",56));



        Iterator iterator = treeSet.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){

        Comparator comparator = new Comparator() {
            //按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }
                throw new RuntimeException("输入的数据类型不匹配");
            }
        };

        TreeSet treeSet = new TreeSet(comparator);

        treeSet.add(new User("Tom",12));
        treeSet.add(new User("Jerry",32));
        treeSet.add(new User("Jim",2));
        treeSet.add(new User("Mike",65));
        treeSet.add(new User("Jack",33));
        treeSet.add(new User("Jack",56));

        Iterator iterator = treeSet.iterator();
        while(iterator.hasNext()){
            iterator.next();
        }



    }

}
