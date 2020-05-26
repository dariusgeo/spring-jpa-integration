package edu.link.jpa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.link.jpa.dao.RegionDao;
import edu.link.jpa.model.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

	@Override
	public List<Region> getAllRegions() {
		
		return readAll("SELECT e FROM Region e", Region.class);
	}

}
