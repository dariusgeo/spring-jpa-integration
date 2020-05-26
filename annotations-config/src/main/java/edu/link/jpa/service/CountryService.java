package edu.link.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.link.jpa.dao.CountryDao;
import edu.link.jpa.model.Country;

@Service
@Transactional
public class CountryService {
	
	@Autowired
	private CountryDao countryDao;
	
	@Transactional(readOnly = true)
	public List<Country> readAllCountries(){
		
		return countryDao.getAllCountries();		
	}

}
