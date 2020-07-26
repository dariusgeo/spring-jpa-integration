package edu.link.jpa.dao.impl;

import edu.link.jpa.dao.RegionDao;
import edu.link.jpa.model.Region;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

	@Override
	public List<Region> getAllRegions() {
		
		return readAll("SELECT e FROM Region e", Region.class);
	}

	@Override
	public List<Region> getAllRegionsWithJoinFetch() {
		TypedQuery<Region> query = typedQuery("SELECT r FROM Region r JOIN FETCH r.countries", Region.class);
		return query.getResultList();
	}

	@Override
	// Still N + 1 Problem. How to fix it?
	public Region getByName(String regionName) {
		//TypedQuery<Region> query = typedQuery("SELECT r FROM Region r WHERE r.name = :regionName", Region.class);
		TypedQuery<Region> query = typedQuery("SELECT r FROM Region r JOIN FETCH r.countries WHERE r.name = :regionName", Region.class);
		query.setParameter("regionName", regionName);
		return query.getSingleResult();
	}


}
