package edu.link.jpa.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.link.jpa.dao.DepartmentDao;
import edu.link.jpa.dao.impl.DepartmentDaoImpl;

public class DepartmentDaoIT {

	private DepartmentDao departmentDao;
	
	@Before
	public void setUp(){
		initDao();
	}
 
	@Test
	public void testGetDepartmentDetails(){
       List<Object> deptDetails = departmentDao.getDepartmentDetails("Alexander", "Hunold");
       Object[] row = (Object [])deptDetails.get(0);
       Arrays.asList(row).stream().forEach(element -> {System.out.println(" >>> " + element);});
	}
	
	private DepartmentDao initDao() {
		if(null == departmentDao){
			departmentDao = new DepartmentDaoImpl();
		}
		
		return departmentDao;
	}
}
