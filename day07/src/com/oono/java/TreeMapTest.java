package com.oono.java;

import org.junit.Test;

import java.util.*;

/**
 * @author oono
 * @date 2020 08 27
 */
public class TreeMapTest {

    //向TreeMap中添加key-value，要求key必须是由同一个数类。
    //因为，TreeMap的用处就是可以按照key来进行排序！（自然排序/定制排序）

    //自然排序
    @Test
    public void test() {

        Map map = new TreeMap();
        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 32);
        User u3 = new User("Jack", 20);
        User u4 = new User("Rose", 18);

        map.put(u1, 98);
        map.put(u2, 89);
        map.put(u3, 76);
        map.put(u4, 100);

        //遍历方式一：
        Set keySet = map.keySet();
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Object value = map.get(key);
            System.out.println(key + "的成绩是：" + value);
        }

        //遍历方式二：
        Set entrySet = map.entrySet();
        for (Object obj : entrySet) {
            Object obj1 = obj;
            Map.Entry entry = (Map.Entry) obj1;
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "的成绩是：" + value);
        }
    }

    //定制排序
    @Test
    public void test2() {

        Map map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw new RuntimeException("输入的数据类型不匹配");
            }
        });

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 32);
        User u3 = new User("Jack", 20);
        User u4 = new User("Rose", 18);
        map.put(u1, 98);
        map.put(u2, 89);
        map.put(u3, 76);
        map.put(u4, 100);

        Set keySet = map.keySet();
        Iterator iterator = keySet.iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
            Object value = map.get(key);
            System.out.println(key + "的成绩是：" + value);
        }

    }


}
