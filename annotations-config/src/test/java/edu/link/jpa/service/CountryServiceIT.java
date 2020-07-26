package edu.link.jpa.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.link.config.AppConfig;
import edu.link.jpa.model.Country;

@Sql({"classpath:sql/data.sql"})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
public class CountryServiceIT {

	@Autowired
	private CountryService underTest;

	@Test
	public void readAllCountries() {
		List<Country> countryList = underTest.readAllCountries();

		countryList.stream().forEach(country  -> System.out.println(country.getName()));

		assertFalse(countryList.isEmpty());
	}

	@Test
	public void readAllCountriesByRegionName() {
		List<Country> countryList = underTest.readAllCountriesByRegionName("Europe");

		countryList.stream().forEach(country -> System.out.println(country.getName()));

		assertFalse(countryList.isEmpty());
	}

}
