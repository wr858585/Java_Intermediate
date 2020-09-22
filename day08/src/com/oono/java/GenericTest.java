package com.oono.java;

import org.junit.Test;

import java.util.*;

/**
 * 泛型
 *
 * 1. JDK5.0新增特性
 *
 * 2. 在集合中使用泛型总结：
 *
 * ① 集合接口或集合类在JDK5.0时，都修改为带泛型的结构！
 * ② 在实例化集合类时，可以指明具体的泛型类型
 * ③ 指明完以后，在集合类或接口中，凡是源码中使用到泛型的位置，都指定为实例化时我们指定的泛型。如add(E e) --> 实例化以后：只能add指定的类型数据（如Integer）
 *      这些内部结构包括：方法，构造器，属性等
 * ④ 注意点：泛型的类型必须是一个类，所以不能是基本数据类型。如果需要用基本数据类型的时候，用wrapper包装类
 * ⑤ 如果实例化时没有指明泛型的类型，默认类型为Object（其实会有警告，不好，但不会报错）
 *
 * 3. 自定义泛型结构（泛型类、泛型接口；泛型方法）
 *
 * ①
 *
 * @author oono
 * @date 2020 09 01
 */
public class GenericTest {

    //在集合使用泛型之前的情况：以ArrayList为例
    @Test
    public void test1() {

        ArrayList list = new ArrayList();
        //需求：存放学生的成绩
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);

        //issue1. 类型不安全（编译时没有类型检查 --> 类型不安全；不如数组）
        list.add("Tom");//不应该允许存放这种数据，编译没有报错

        for (Object score : list) {

            //issue2. 强转时，有可能出现ClassCastException类型转换异常
            int studentScore = (int) score;

            System.out.println(studentScore);

        }
    }

    //引入泛型后：
    @Test
    public void test2() {

        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);

//        list.add("Tom");编译时就会进行类型检查，保证数据类型的安全

/*  方式一：用for遍历
        for (Integer score : list){
            //避免了强转操作
            int studentScore = score;
            System.out.println(studentScore);
        }
*/
        //方式二：用iterator遍历
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int studentScore = iterator.next();
            System.out.println(studentScore);
        }
    }

    //在集合中使用泛型的情况2：HashMap
    @Test
    public void test3() {

//        Map<String, Integer> map = new HashMap<String, Integer>();
        //JDK7.0新特性：类型推断
        Map<String,Integer> map = new HashMap<>();

        map.put("Tom",87);
        map.put("Jerry",87);
        map.put("Jack",67);

//        map.put(123,"abc");运用泛型后这种数据通不过了

        //泛型的嵌套
        Set<Map.Entry<String,Integer>> entry = map.entrySet();
        Iterator<Map.Entry<String,Integer>> iterator = entry.iterator();

        while(iterator.hasNext()){
            Map.Entry<String,Integer> e = iterator.next();
            String key = e.getKey();
            Integer value  = e.getValue();
            System.out.println(key + "----" + value);
        }



    }


}
