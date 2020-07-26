package com.oono.team.domain;

public class Architect extends Designer{

	private int stock;
	
	public Architect(){
		
	}
	public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock){
		super(id, name, age, salary, equipment, bonus);
		this.stock = stock;
	}
	
	public int getStock(){
		return stock;
	}
	public void setStock(int stock){
		this.stock = stock;
	}
	
	//重写toString
	public String toString(){
		return getDetails() + "\t架构师\t" + getStatus() + "\t" + getBonus() + "\t" + stock + "\t" + getEquipment().getDescription();
	}
	
	//1/2	马化腾	32	18000.0		架构师		15000.0		2000
	public String getDetailsForTeam(){
		return getTeamBasicInfo() + "\t架构师\t" + getBonus() + "\t" + stock; 
	}
}
