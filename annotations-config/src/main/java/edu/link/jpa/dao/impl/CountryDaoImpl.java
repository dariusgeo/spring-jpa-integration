package edu.link.jpa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.link.jpa.dao.CountryDao;
import edu.link.jpa.model.Country;

@Repository
public class CountryDaoImpl extends BaseDaoImpl<Country> implements CountryDao{

	@Override
	public List<Country> getAllCountries() {
		
		return readAll("SELECT e FROM Country e", Country.class);
	}

}
