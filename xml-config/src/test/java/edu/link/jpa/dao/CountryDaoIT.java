package edu.link.jpa.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.link.jpa.dao.CountryDao;
import edu.link.jpa.dao.impl.CountryDaoImpl;
import edu.link.jpa.model.Country;

public class CountryDaoIT {


	private CountryDao countryDao;
	
	@Before
	public void setUp(){
		initDao();
	}
 
	@Test
	public void testGetAllEmployees(){
		List<Country> allEmployees = countryDao.getAllCountries();
		
		countryDao.getAllCountries().stream().forEach(emp -> System.out.println( emp.getName()));
		assertEquals(false, allEmployees.isEmpty());
	}
	
	private CountryDao initDao() {
		if(null == countryDao){
			countryDao = new CountryDaoImpl();
		}
		
		return countryDao;
	}
}
