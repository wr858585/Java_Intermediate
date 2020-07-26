package com.oono.team.domain;

public class Employee {

	private int id;
	private String name;
	private int age;
	private double salary;
	
	public Employee(){
		
	}
	public Employee(int id, String name, int age, double salary){
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
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
	public double getSalary(){
		return salary;
	}
	public void setSalary(double salary){
		this.salary = salary;
	}
	
	//把重复的结构封装到一个方法中，方便子类调用（否则的话子类的子类Designer是不好调的，没有super.super这种东西）
	public String getDetails(){
		return id + "\t" + name + "\t" + age + "\t" + salary;
	}
	
	//重写toString方法，让sysout打印UI中要显示的信息，而非打印出每个对象的地址
	public String toString(){
		return getDetails();
	}
	
}
