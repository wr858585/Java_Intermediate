package com.oono.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oono
 * @date 2020 08 07
 */
public class ListExer {

    @Test
    public void testListRemove(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//[1,2]
    }

    private static void updateList(List list){
        list.remove(2);
        //list.remove(new Integer(2));则会删除ele为2的元素
    }

}
