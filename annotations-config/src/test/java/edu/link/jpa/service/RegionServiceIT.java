package edu.link.jpa.service;

import edu.link.config.AppConfig;
import edu.link.jpa.model.Country;
import edu.link.jpa.model.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Sql({"classpath:sql/data.sql"})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
public class RegionServiceIT {

    @Autowired
    private RegionService underTest;

    @Test
    public void readAllRegions() {
        List<Region> regionList = underTest.readAllRegions();

        regionList.stream().forEach(region  -> System.out.println(region.getName()));

        assertFalse(regionList.isEmpty());
    }

    @Test
    public void readRegionByName() {
        List<Country> countries = underTest.readCountriesByRegionName("Europe");

        countries.stream().forEach(country -> System.out.println(country.getName()));

        assertFalse(countries.isEmpty());
    }

    @Test
    public void readAllRegions_NplusOneProblem() {
        List<Region> regionList = underTest.readAllRegions();

        regionList.stream().forEach(region  -> {
            System.out.println(region.getName());
            region.getCountries().stream().forEach(country -> {
                System.out.println(" >> " + country.getName());
            });
        });

        assertFalse(regionList.isEmpty());
    }

    @Test
    public void getAllRegionsWithJoinFetch() {
        List<Region> regionList = underTest.getAllRegionsWithJoinFetch();

        regionList.stream().forEach(region  -> {
            System.out.println(region.getName());
            region.getCountries().stream().forEach(country -> {
                System.out.println(" >> " + country.getName());
            });
        });

        assertFalse(regionList.isEmpty());
    }

}
