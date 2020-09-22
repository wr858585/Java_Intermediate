package com.oono.java;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author oono
 * @date 2020 08 27
 */
public class PropertiesTest {

    //Properties类是HashTable（Map的古老实现类）的子类，通常用来处理配置文件 --> key和value都是String类型

    public static void main(String[] args) throws Exception{

        Properties props = new Properties();

        FileInputStream fis = new FileInputStream("jdbc.properties");
        props.load(fis);//加载流对应的文件
        String name = props.getProperty("name");
        String password = props.getProperty("password");

        System.out.println("name=" + name + ", password=" + password);


    }


}
