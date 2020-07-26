package com.oono.team.service;

import com.oono.team.domain.*;
//import com.oono.team.domain.Architect;
//import com.oono.team.domain.Designer;
//import com.oono.team.domain.Employee;
//import com.oono.team.domain.Equipment;
//import com.oono.team.domain.NoteBook;
//import com.oono.team.domain.PC;
//import com.oono.team.domain.Printer;
//import com.oono.team.domain.Programmer;

/**
 * @description 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法
 * @author oono
 * @version 1.0
 *
 */
public class NameListService {

	private Employee[] employees;
	
	/**
	 * 给employees及其数组元素进行初始化
	 */
	public NameListService(){
//		0. 这里是重难点
//		1. 根据项目提供的Data类构建相应大小的employees数组
//		2. 再根据Data类中的数据构建不同的对象，包括Employee, Programmer, Designer, Architect对象，
//				以及相关联的Equipment子类的对象
//		3. 将对象存于数组中
		employees = new Employee[Data.EMPLOYEES.length];
		
		for(int i = 0; i < employees.length; i++){
			
			//获取员工的类型（是EMPLOYEE/PROGRAMMER/DESIGNER/ARCHITECT，编号不一样）
			int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
			
			//获取Employee的四个基本信息（方便switch-case中使用，减少代码量）
			int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
			String name = Data.EMPLOYEES[i][2];
			int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
			double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);
			
			Equipment equipment = null;//先初始化，不要获取（调用方法），因为EMPLOYEE的话没有设备，会err
			switch(type){
			case Data.EMPLOYEE://不要些case 10，这样不知道10是什么，可读性/维护性很差。EMPLOYEE是常量，又见名知意
				employees[i] = new Employee(id, name, age, salary);//这样就简洁许多了，在上面获取以后可以复用
				break;
			case Data.PROGRAMMER:
				//equipment如何放进去也是重难点，必须要用方法去new对象，因为和employee一样编号不同new的对象不同
				//所以要把这种复杂的操作和指令封装到方法中
				equipment = createEquipment(i);
				employees[i] = new Programmer(id, name, age, salary, equipment);
				break;
			case Data.DESIGNER:
				equipment = createEquipment(i);
				employees[i] = new Designer(id, name, age, salary, equipment, 
						Double.parseDouble(Data.EMPLOYEES[i][5]));
				break;
			case Data.ARCHITECT:
				equipment = createEquipment(i);
				employees[i] = new Architect(id, name, age, salary, equipment, 
						Double.parseDouble(Data.EMPLOYEES[i][5]), 
						Integer.parseInt(Data.EMPLOYEES[i][6]));
				break;
				
			}
		}
		
	}
	
	/**
	 * @description 获取指定index上的员工的设备
	 * @param i
	 * @return
	 */
	private Equipment createEquipment(int index) {
		// TODO Auto-generated method stub
		
		//获取设备的类型（编号是多少，代表是什么设备）
		int key = Integer.parseInt(Data.EQUIPMENTS[index][0]);
		
		Equipment equipment = null;//声明的时候习惯上初始化比较好，这里否则return equipment会报错
		//因为有可能switch-case是进不去的，输入的index不匹配任何三种情况，则equipment没有new，必须要null才能return，否则没有机会初始化
		//获取Equipment中各个实现类的1个基本信息：model/name(第二个信息有的String有的double，就不做统一处理了）
		String model = Data.EQUIPMENTS[index][1];
		
		switch(key){
		case Data.PC:
			equipment = new PC(model, Data.EQUIPMENTS[index][2]);
			break;
		case Data.NOTEBOOK:
			equipment = new NoteBook(model, Integer.parseInt(Data.EQUIPMENTS[index][2]));
			break;
		case Data.PRINTER:
			equipment = new Printer(model, Data.EQUIPMENTS[index][2]);
		}
		
		return equipment;//超级注意：这里返回的并非Equipment这个接口，而是其实现类的对象！（多态）
		//也可以直接在case中 return new xxx(...)，类似于匿名实现，都不需要声明equipment
	}

	/**
	 * @description 获取当前所有的员工（相当于就是私有属性employees的getter）
	 * @return
	 */
	public Employee[] getAllEmployees(){
		return employees;//employees已经通过NameListService()方法填充好了
	}
	
	/**
	 * @description 获取指定ID的员工对象
	 * @param id
	 * @return
	 * @throws TeamException
	 */
	public Employee getEmployee(int id) throws TeamException{
		for(int i = 0; i < employees.length; i++){
			if(employees[i].getId() == id){//这里是基本数类用==。如果是Integer类的id，则只能用.equals，因为==比较的是两个的address了
				return employees[i];
			}
		}
		throw new TeamException("员工不存在");
		
	}
	
	
}
