package com.oono.team.service;

public class TeamException extends Exception{

	public static final long serialVersionUID = -12312312412L;
	
	public TeamException(){
		
	}
	public TeamException(String msg){
//		this.msg = msg;这个写法是不对的，msg是父类Exception构造器中写的this.msg = msg;
		super(msg);
	}
}
