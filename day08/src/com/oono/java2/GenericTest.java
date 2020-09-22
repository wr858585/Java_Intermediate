package com.oono.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 1. 泛型在继承方面的说明
 *
 * 2. 通配符的使用
 *
 *
 * @author oono
 * @date 2020 09 07
 */
public class GenericTest {

    /*
    1. 泛型在继承方面的体现
    --> 类A是类B的父类，G<A>和G<B>不具备子父类关系，即是说二者是完全并列的关系
    --> 类A是类B的父类，A<G>和B<G>则仍然具有子父类关系，A<G>是B<G>的父类
     */

    @Test
    public void test1(){

        Object obj = null;
        String str = null;

        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;

        arr1 = arr2;

        List<Object> list1 = null;
        List<String> list2 = null;

//        list1 = list2;
     }

     @Test
    public void test2(){

        List<String> list1 = null;
        ArrayList<String> list2 = null;

        list1 = list2;//这是可以的

        //相当于这个多态的写法
        List<String> list4 = new ArrayList<>();

     }

     /*
     2. 通配符的使用
     ① 通配符：?
     ② 类A是类B的父类，则G<A>和G<B>不具有子父类关系，是并列的关系，但二者有公共的父类G<?>
      */

     @Test
     public void test3(){

         List<Object> list1 = null;
         List<String> list2 = null;

         List<?> list = null;

         list = list1;
         list = list2;

         //
         List<String> list3 = new ArrayList<>();
         list3.add("AA");
         list3.add("BB");
         list3.add("CC");

         list = list3;

         //添加：对于List<?>，就不能向其添加数据了
         //唯一例外：可以加null
//         list.add("DD");
//         list.add(123);
         list.add(null);

         //获取（读取）：允许读取数据，读取的数据类型是Object
         Object o = list.get(0);
         System.out.println(o);

     }

     public void print(List<?> list){
         Iterator<?> iterator = list.iterator();
         while(iterator.hasNext()){
             Object obj = iterator.next();
             System.out.println(obj);
         }
     }

     /*
     3. 有限制条件的通配符的使用：
     ① ? extends A的理解：G<? extends A> --> 表明它可以接收<=类A的类型
     ② ? super A的理解：G<? super A> --> 表明它可以接收>=类A的类型

      */

    @Test
    public void test4(){

        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<>();
        List<Person> list4 = new ArrayList<>();
        List<Object> list5 = new ArrayList<>();

        list1 = list3;//可以
        list1 = list4;//可以
//        list1 = list5;//不行

//        list2 = list3;//不行
        list2 = list4;//可以
        list2 = list5;//可以

        //读取数据
        list1 = list3;
        Person p = list1.get(0);//多态！
//        Student s = list1.get(0);

        list2 = list4;
        Object o = list2.get(0);

//        Person p1 = list2.get(0);

        //写入数据

//        这两种都不能通过
        //原因：因为左边声明的数类是<=Person，那么可以小到无穷，可能比Person小，可能比Student小，可能比什么都小
        //所以，不能将左边的引用指向右边new的对象
        //譬如Object obj = new Person()则可以（多态）；但Person p = new Object()肯定报错
//        list1.add(new Student());
//        list1.add(new Person());

        //这两种都可以通过，因为 ? super Person表示>=Person，所以左边可能声明的最小数类就是Person
        //所以，右边new Person类可以（正常情况），new Person类的子类也可以（多态）
        list2.add(new Person());
        list2.add(new Student());





    }

}
