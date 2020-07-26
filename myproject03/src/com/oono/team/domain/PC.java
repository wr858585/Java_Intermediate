package com.oono.team.domain;

public class PC implements Equipment{

	private String model;//机器型号
	private String display;//显示器名称
	
	public PC(){//一般习惯带上一个空参的，实际开发中免得回来加。经常如果不写这个的话后面也会有需求
		
	}
	public PC(String model, String display){
		this.model = model;
		this.display = display;
	}
	
	public String getModel(){
		return model;
	}
	public void setModel(String model){
		this.model = model;
	}
	public String getDisplay(){
		return display;
	}
	public void setDisplay(String display){
		this.display = display;
	}
	
	public String getDescription(){
		return model + "(" + display + ")";//按照实际需求的输出样式来  
	}
}
