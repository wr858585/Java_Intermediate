package com.oono.java1;

import java.util.Objects;

/**
 * @author oono
 * @date 2020 08 07
 */
public class User {

    private String name;
    private int age;

    public User(){

    }
    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String toString(){
        return "User{" + "name=" + name + ", age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Invoking User equals()...");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
