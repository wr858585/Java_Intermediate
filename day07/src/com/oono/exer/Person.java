package com.oono.exer;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
