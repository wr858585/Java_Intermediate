package com.oono.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1. 说明：
 * ① Java中的对象，正常情况下，只能进行比较==或!=，不能使用>或<的
 * ② 但实际开发中，我们需要对多个对象进行排序（通过比较它们的属性），则需要比较对象的大小 --> 通过Comparable或Comparator接口实现
 *
 * 2. Comparable接口的使用
 * ① 所有implements Comparable接口的实现类都需要重写方法compareTo()，来完成所需相关排序操作，否则就算使用Arrays.sort()，Collection.sort()也会出ClassCastException
 * ② 实现Comparable的方式叫自然排序
 *
 * 3. Comparator接口的使用
 * ① 在调用各类Xxx.sort()方法时候，调用重载的方法Xxx.sort(xxx, new Comparator(){...});来匿名实现一次接口Comparator
 *      为何要匿名实现？ --> 因为这些自定义的逻辑通常就是one-off，根本不需要造一个类来实现Comparator接口，也有违Comparator接口自定义逻辑的初衷（因为写成类来实现，就说明之后有其他对象都是调用一样的逻辑）
 *
 * @author oono
 * @date 2020 07 22
 */
public class CompareTest {

    /*
    Comparable的使用举例（自然排序）
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

    /*
    Comparator的使用：定制排序
    1. intuition
    ① 实现了Comparable接口，但其排序规则不太适合需求，可以用Comparator接口自定义排序逻辑
    ② 没有实现Comparable接口，但又不方便修改源代码使得其实现Comparable接口
    2. 重写compare()方法：
    ① 格式：compare(Object o1, Object o2)

     */

    @Test
    public void test2(){

        String arr[] = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        Arrays.sort(arr);

        System.out.println(arr);//[Ljava.lang.String;@78e03bb5
        System.out.println(Arrays.toString(arr));//[AA, CC, DD, GG, JJ, KK, MM]

        Arrays.sort(arr, new Comparator(){//匿名实现Comparator接口
            //自定义排序规则：按照字符串从大到小排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof String){
                    String s1 = (String)o1;
                    String s2 = (String)o2;
                    return -s1.compareTo(s2);
                }
                throw new RuntimeException("输入的数据类型不一致");
            }
        });

        System.out.println(arr);//[Ljava.lang.String;@78e03bb5
        System.out.println(Arrays.toString(arr));//[MM, KK, JJ, GG, DD, CC, AA]

    }

    @Test
    public void test3(){

        Goods arr[] = new Goods[4];
        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("dellMouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);

        Arrays.sort(arr,new Comparator(){
            //自定义排序逻辑：先按照商品名称从小到大排序，再按照价格从大到小排序
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods)o1;
                    Goods g2 = (Goods)o2;
/*  自己写的逻辑，太复杂了
                    if(g1.getName().compareTo(g2.getName()) > 0){
                        return 1;
                    }else if(g1.getName().compareTo(g2.getName()) < 0){
                        return -1;
                    }else{
                        if(g1.getPrice() > g2.getPrice()){
                            return -1;
                        }else if(g1.getPrice() < g2.getPrice()){
                            return 1;
                        }else{
                            return 0;
                        }
                    }
*/
                    if(g1.getName().equals(g2.getName())){
                        return -Double.compare(g1.getPrice(), g2.getPrice());
                    }
                    return g1.getName().compareTo(g2.getName());

                }
                throw new RuntimeException("输入的数据类型不一致");
            }
        });

        System.out.println(arr);//[Lcom.oono.java.Goods;@78e03bb5
        System.out.println(Arrays.toString(arr));//[Goods{name='dellMouse', price=43.0}, Goods{name='huaweiMouse', price=65.0}, Goods{name='lenovoMouse', price=34.0}, Goods{name='xiaomiMouse', price=12.0}]


    }


}
