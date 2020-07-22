package com.oono.java;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1. 说明：
 * ① Java中的对象，正常情况下，只能进行比较==或!=，不能使用>或<的
 * ② 但实际开发中，我们需要对多个对象进行排序（通过比较它们的属性），则需要比较对象的大小 --> 通过Comparable或Comparator接口实现
 *
 * 2. Comparable接口的使用
 * ① 所有implements Comparable接口的实现类都需要重写方法compareTo()，来完成所需相关排序操作，否则就算使用Arrays.sort()，Collection.sort()也会出ClassCastException
 * ② 实现Comparable的方式叫自然排序
 *
 * @author oono
 * @date 2020 07 22
 */
public class CompareTest {

    /*
    Comparable的使用举例
    1. 像String类，包装类等，都实现了Comparable接口，所以都要重写CompareTo()方法，给出了比较两个对象大小的方式
    2. 像String类，包装类等重写过CompareTo()方法后，一般是从小到大的顺序排列
    3. 重写compareTo()的规则：
    ① 如果调用当前方法的对象this大于形参对象obj，则返回正整数
    ② 如果调用当前方法的对象this小于形参对象obj，则返回负整数
    ③ 如果this和形参相等，则返回0
    4. 对于自定义类，一定要实现Comparable接口，才能使用sort()等方法 --> 由于是自定义类，需求古怪，所以重写compareTo()方法来指明如何排序更为妥当

     */

    @Test
    public void test(){

        String arr[] = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};

        //数组有Arrays.sort()方法，集合也有Collections.sort()方法
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));//[AA, CC, DD, GG, JJ, KK, MM]

    }

    @Test
    public void test1(){

        Goods arr[] = new Goods[4];
        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("dellMouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));//[Goods{name='xiaomiMouse', price=12.0}, Goods{name='lenovoMouse', price=34.0}, Goods{name='dellMouse', price=43.0}, Goods{name='huaweiMouse', price=65.0}]

    }


}
