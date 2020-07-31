package com.oono.team.junit;


import com.oono.team.domain.Employee;
import com.oono.team.service.NameListService;
import com.oono.team.service.TeamException;
import org.junit.Test;

/**
 * 对NameListService类的测试	
 * @author oono
 *
 */
public class NameListServiceTest {

	@Test
	public void testGetAllEmployees(){
		NameListService service = new NameListService();
		Employee[] employees = service.getAllEmployees();
		for(int i = 0; i < employees.length; i++){
			System.out.println(employees[i]);
		}
	}
	
	@Test
	public void testGetEmployee(){
		NameListService service = new NameListService();
		int id = 2;
		try{
		Employee employee = service.getEmployee(id);
		System.out.println(employee);
		}catch(TeamException e){
			System.out.println(e.getMessage());
		}
	}
	
}
