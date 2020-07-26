package com.oono.team.service;
/**
 * @description 表示员工状态：有且仅有三种状态，所以类似于单例模式，这个是三例模式，只有三个实例
 * 那么意味着要按照单例模式一样，构造器要私有化
 * @author oono
 * @version 1.0
 */
public class Status {

	private final String NAME;
	
	public static final Status FREE = new Status("FREE");
	public static final Status BUSY = new Status("BUSY");
	public static final Status VACATION = new Status("VACATION");
	
	private Status(String name){//不允许外面造对象了
		this.NAME = name;
	}
	
	public String getNAME(){
		return NAME;	
	}
	//不用提供setter了，因为NAME是常量
	
	public String toString(){
		return NAME;//不是name，严格区分大小写
	}
	
}
