package com.oono.exer1;

/**
 * 定义一个Employee类
 * 包含：private成员变量name, age, birthday;其中birthday为MyDate类的对象
 * 提供getter/setter
 * 重写toString()，输出relevant info
 *
 * @author oono
 * @date 2020 08 08
 */
public class Employee implements Comparable{

    private String name;
    private int age;
    private MyDate birthday;

    public Employee(){

    }
    public Employee(String name, int age, MyDate birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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
    public MyDate getBirthday(){
        return birthday;
    }
    public void setBirthday(MyDate birthday){
        this.birthday = birthday;
    }

    public String toString(){
        return "Employee{name=" + name + ", age=" + age + ", birthday=" + birthday + "}";
    }

    public int compareTo(Object o){
        if(o instanceof Employee){
            Employee e = (Employee)o;
            return this.name.compareTo(e.getName());
        }
        throw new RuntimeException("传入的数据类型不一致");
    }



}
