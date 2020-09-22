package com.oono.java1;

import org.junit.Test;

/**
 * @author oono
 * @date 2020 09 04
 */
public class DAOTest {

    @Test
    public void test1(){

        CustomerDAO dao1 = new CustomerDAO();
        dao1.add(new Customer());//泛型就规定了这里只能传Customer类的实例，因为new dao1的时候就是CustomerDAO类的

        StudentDAO dao2 = new StudentDAO();
        dao2.add(new Student());//这里就只能操作StudentDAO表里的数据了



    }


}
