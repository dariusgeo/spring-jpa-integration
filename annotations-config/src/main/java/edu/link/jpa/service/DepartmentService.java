//package edu.link.jpa.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import edu.link.jpa.dao.DepartmentDao;
//
//@Service
//public class DepartmentService {
//	
//	@Autowired
//	private DepartmentDao departmentDao;
//	
//	@Transactional(readOnly = true)
//	public List<Object> getDepartmentByEmployeeFullname(String employeeFirstName, String employeeLastname) {
//		return departmentDao.getDepartmentDetails(employeeFirstName, employeeLastname);
//	}
//
//}
