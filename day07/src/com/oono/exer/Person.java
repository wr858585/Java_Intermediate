package com.oono.exer;

/**
 * @author oono
 * @date 2020 08 14
 */
public class Person {

    int id;
    String name;

    public Person(){

    }
    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return "Person{name=" + name + ", id=" + id + "}";
    }





}
