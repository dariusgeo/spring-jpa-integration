package edu.link.jpa.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.link.jpa.dao.impl.EmployeeDaoImpl;
import edu.link.jpa.model.Employee;

public class EmployeeDaoIT {
	
	private EmployeeDao employeeDao;
	
	@BeforeEach
	public void setUp(){
		initDao();
	}
 
	@Test
	public void testGetAllEmployees(){
		List<Employee> allEmployees = employeeDao.getAllEmployees();
		
		employeeDao.getAllEmployees().stream().forEach(emp -> System.out.println( emp.getFirstname()));
		assertEquals(false, allEmployees.isEmpty());
	}
	
	@Test
	public void testGetEmployeeById(){
		Employee emp = employeeDao.getEmployeeById(100l);
		System.out.println( emp.getFirstname());
		assertNotNull(emp.getFirstname());
	}
	
	@Test
	public void testGetEmployeeByEmail(){
		Employee emp = employeeDao.getEmployeeByEmail("NGREENBE");
		System.out.println( emp.getFirstname());
		assertNotNull(emp.getFirstname());
	}
	
	@Test
	public void test_Dynamic_Query_getEmployeesHavingSalaryOverAmount(){
		List<Employee> employeeList = employeeDao.getEmployeesHavingSalaryOverAmount("Finance", new Double(1500));
		System.out.println(employeeList.size());
		assertFalse(employeeList.isEmpty());
	}
	
	@Test
	public void test_Named_Query_findByDepartment(){
		List<Employee> employeeList = employeeDao.getEmployeesByDepartment("Finance");
		System.out.println(employeeList.size());
		assertFalse(employeeList.isEmpty());
	}
	
	private EmployeeDao initDao() {
		if(null == employeeDao){
			employeeDao = new EmployeeDaoImpl();
		}
		
		return employeeDao;
	}

}
