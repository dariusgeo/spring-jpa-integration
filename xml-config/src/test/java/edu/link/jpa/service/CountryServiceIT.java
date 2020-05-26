package edu.link.jpa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.link.jpa.model.Country;

@RunWith(SpringJUnit4ClassRunner.class) //- JUnit 4
@ContextConfiguration(locations = {"classpath:spring/application-config.xml"})
@Transactional(transactionManager = "transactionManager")
public class CountryServiceIT {
	
	@Autowired
	private CountryService countryService;
	
	
	@Test
	public void testGetAllCountries(){
		List<Country> countryList = countryService.getAllCountries();
		
		countryList.stream().forEach(emp -> System.out.println( emp.getName()));
		
		assertEquals(false, countryList.isEmpty());
	}	

}
