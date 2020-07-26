package com.oono.team.domain;

import com.oono.team.service.Status;

public class Programmer extends Employee{
	
	private int memberId;//开发团队中的id（TID：Team ID）
	private Status status = Status.FREE;//Status是枚举类，之后可以换种写法
	private Equipment equipment;
	
	public Programmer(){
		
	}
	public Programmer(int id, String name, int age, double salary, Equipment equipment){
		super(id, name, age, salary);
		this.equipment = equipment;
	}
	
	public int getMemberId(){
		return memberId;
	}
	public void setMemberId(int memberId){
		this.memberId = memberId;
	}
	public Status getStatus(){
		return status;
	}
	public void setStatus(Status status){
		this.status = status;
	}
	public Equipment getEquipment(){
		return equipment;
	}
	public void setEquipment(Equipment equipment){
		this.equipment = equipment;
	}

	//重写toString
	public String toString(){
		return super.toString() + "\t程序员\t" + status + "\t\t\t"
	//把重复的结构封装进功能后（Employee类中），也可以不用super.toString了，直接getDetails()
				+ equipment.getDescription();
	//注意，status，equipment都是自定义类，toString方法是地址。所以要么需要重写，要么equipment调用自定义的getDescription方法
	}
	
	public String getTeamBasicInfo(){
		return memberId + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t"
				+ getSalary();
	}
	
	//2/6	任志强	22	6800.0	程序员
	public String getDetailsForTeam(){
		return getTeamBasicInfo() + "\t程序员"; 
	}
}
