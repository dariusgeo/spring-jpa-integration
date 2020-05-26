package edu.link.jpa.dao;

import java.util.List;

import edu.link.jpa.model.Employee;

public interface EmployeeDao {

	Employee getEmployeeById(Long id);
	
	void save(Employee employee);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeByEmail(String email);
	
	List<Employee> getEmployeesHavingSalaryOverAmount(String departmentName, Double salary);
	
	void updateEmployee(Employee employee);
	
	List<Employee> getEmployeesByDepartment(String departmentName);
}
