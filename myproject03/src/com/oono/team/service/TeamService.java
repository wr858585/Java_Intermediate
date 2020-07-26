package com.oono.team.service;

import com.oono.team.domain.Architect;
import com.oono.team.domain.Designer;
import com.oono.team.domain.Employee;
import com.oono.team.domain.Programmer;

/**
 * @description 关于开发团队成员的管理：添加、删除等
 * @author oono
 *
 */
public class TeamService {
	
	private static int counter = 1;//目的：给memberId赋值使用（帮助自增1）
	private final int MAX_NUMBER = 5;//限制开发团队的人数
	private Programmer[] team = new Programmer[MAX_NUMBER];//保存开发团队的成员
	private int total;//记录开发团队中实际的人数
	
	public TeamService(){//可以不写，系统会自己提供这个构造器
		
	}
	
	/**
	 * @description 获取开发团队中的所有成员
	 * @return
	 */
	public Programmer[] getTeam(){
//		return team; 这是不对的。因为可能team中实际只有2个人，则后面这些null去调方法会空指针
//		solution：再造一个数组，使得team中的成员去赋给新的数组，则新数组不会有空的元素null
		Programmer[] team = new Programmer[total];
		for(int i = 0; i < team.length; i++){
//			用if是没有必要的，因为数组是内存中一块连续的区域，所以不空的元素一定是挨着的。
//			if(this.team[i] != null){
//				team[i] = this.team[i];
//			}
			team[i] = this.team[i];
			//要能分清谁是谁，team是局部变量（此方法中的）
			//this.team是当前（类的）对象的team，即成员变量（属性）team
			//可以类比构造器写this.field = field，是把传入的形参field赋值给成员变量（属性）this.field
		}
		return team;
	}
	
	/**
	 * @description 将指定的员工添加到开发团队中
	 * @param e
	 * @throws TeamException
	 */
	public void addMember(Employee e) throws TeamException{
		//成员已满，无法添加
		if(total >= MAX_NUMBER){
			throw new TeamException("成员已满，无法添加");
		}
		//该成员不是开发人员，无法添加
		if(!(e instanceof Programmer)){//新的写法，理解一下
			throw new TeamException("该成员不是开发人员，无法添加");
		}
		//该员工已在本开发团队中
		if(isExist(e)){
			throw new TeamException("该员工已在本开发团队中");
		}
		//该员工已是某团队成员
		//该员工正在休假，无法添加
		Programmer p = (Programmer)e;//一定不会强转失败，出现ClassCastException，否则第二个if就进去了，然后被异常抛出去了
		if("BUSY".equalsIgnoreCase(p.getStatus().getNAME())){
			//比if(p.getStatus().getNAME().equals("BUSY"))要好
			//因为"BUSY"是一个确定是字符串，一定可以调equals()方法
			//而p.getStatus().getNAME()可能出现null，调equals会增加空指针的风险
			throw new TeamException("该员工已是某团队成员");
		}else if("VACATION".equalsIgnoreCase(p.getStatus().getNAME())){
			throw new TeamException("该员工正在休假，无法添加");
		}
		//团队中至多只能有一名架构师
		//团队中至多只能有两名设计师
		//团队中至多只能有三名程序员
		
		//获取team已有成员中的架构师、设计师、程序员个数
		int numOfArch = 0, numOfDes = 0, numOfPro =0;
		for(int i = 0; i < total; i++){//而非i<team.length，否则又有可能有空的元素
			if(team[i] instanceof Architect){
				numOfArch++;
//				continue;添加continue的话逻辑不一样，是理解为：设计师、架构师不占用程序员名额
			}else if(team[i] instanceof Designer){
				numOfDes++;
//				continue;
			}else if(team[i] instanceof Programmer){
				numOfPro++;
			}
		}
		
		//正确的逻辑
		if(p instanceof Architect){
			if(numOfArch >= 1){
				throw new TeamException("团队中至多只能有一名架构师");
			}
		}else if(p instanceof Designer){
			if(numOfDes >= 2){
				throw new TeamException("团队中至多只能有两名设计师");
			}
		}else{
			if(numOfPro >= 3){
				throw new TeamException("团队中至多只能有三名程序员");
			}
		}
		
		//错误的逻辑（尤其是这种很复杂的逻辑，都要避免。逻辑不清晰，十分易错，看似简洁，实则混乱）
//		if(p instanceof Architect && numOfArch >= 1){
//			throw new TeamException("团队中至多只能有一名架构师");
//		}else if(p instanceof Designer && numOfDes >=2){
//			throw new TeamException("团队中至多只能有两名设计师");
//		}else{
//			throw new TeamException("团队中至多只能有三名程序员");
//		}
		//解释：可能第一个条件进不去（因为&&比一个条件更难进去了！），第二个能进去。
		//比如：团队已经有两个设计师了，然后再添加一个架构师，那第一个进不去，第二个能进，抛异常，但实际上不应该抛异常，能加一个架构师
		//原理：第一种逻辑很好的把握了if-else_if-else之间是互斥的，进去一个就不会进入第二个
		//		所以当架构师满足第一个条件进入第一个if后，就不会再进入之后的else_if和else。
		//		逻辑直接，没有绕，这种才是好的逻辑
		
		//最后，将p(或e)t添加到现有的team中
		team[total++] = p;
		//开发团队中p的属性要赋值
		p.setStatus(Status.BUSY);//setStatus的形参是Status类的obj，不确定可以看一下原方法
		p.setMemberId(counter++);
		//note：这里上面三行代码的顺序可以互换。
		//因为p是一个引用数类（Programmer类的实例），赋值给team[total]这个元素是传的address
		//那么，这个obj的属性status,memberId等等什么时候修改都不影响传递的是这个对象的address
		//不会传个其他的obj
		
		
	}
	
	/**
	 * @description 判断指定的员工是否已经存在于现有的开发团队中
	 * @param e
	 * @return
	 */
	private boolean isExist(Employee e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < total; i++){
			return team[i].getId() == e.getId();
		}
		return false;
	}
	
	/**
	 * @descriptino 从团队中删除成员
	 * @param memberId
	 * @throws TeamException
	 */
	public void removeMember(int memberId) throws TeamException{
		int i = 0;
		for(; i < total; i++){//这样写也是可以的，相当于没有初始条件，但判断体就用了i，i又等于0，所以一样
			if(team[i].getMemberId() == memberId){
				team[i].setStatus(Status.FREE);
				break;//提高效率
			}
		}
		//未找到指定memberId的情况（如果用isFlag的方式处理也可以的，这里是新的思路，学习）
		if(i == total){
			throw new TeamException("找不到指定memberId的员工，删除失败");
		}
		
		//删除一个元素的必做事项：
		//需要把后面一个元素覆盖前一个元素，最后一个元素置空成null，然后total--
		for(int j = i + 1; j < total; j++){
			team[j - 1] = team[j];
		}
		
//		team[total-1] = null;
//		total--;
//		可以合并为：
		team[--total] = null;
		
	}
	

}
