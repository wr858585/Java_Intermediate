package com.oono.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 1. 实际开发中，Set接口经常用于去除List中的重复值，得到一个没有重复元素的List
 *
 * @author oono
 * @date 2020 08 10
 */
public class SetAsFilterTest {

    public List duplicateList(List list){

        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);

    }

    @Test
    public void test1(){

        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
//        list.add(1);
//        list.add(2);
//        list.add(2);
//        list.add(4);
//        list.add(4);
        List list1 = duplicateList(list);

        for(Object obj : list1){
            System.out.println(obj);
        }

    }

    @Test
    public void test2(){
        int i = 1;
        int j = 0;
//        i = i + 1;
//        j = i++;
//        i = i++;
        System.out.println(i);//
    }

}
