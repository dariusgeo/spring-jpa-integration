package edu.link.jpa.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.link.jpa.dao.RegionDao;
import edu.link.jpa.dao.impl.RegionDaoImpl;
import edu.link.jpa.model.Region;

public class RegionDaoIT {

private RegionDao regionDao;
	
	@BeforeEach
	public void setUp(){
		initDao();
	}
 
	@Test
	public void testGetAllRegions(){
		List<Region> allRegions = regionDao.getAllRegions();
		
		allRegions.stream().forEach(reg -> System.out.println(reg.getName()));
		assertEquals(false, allRegions.isEmpty());
	}
	
	private RegionDao initDao() {
		if(null == regionDao){
			regionDao = new RegionDaoImpl();
		}
		
		return regionDao;
	}
}
