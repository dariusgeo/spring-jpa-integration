package edu.link.jpa.dao;

import java.util.List;

public interface DepartmentDao {

	List<Object> getDepartmentDetails(String employeeFirstName, String employeeLastname);
}
