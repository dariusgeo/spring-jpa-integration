package edu.link.jpa.service;

import edu.link.jpa.dao.RegionDao;
import edu.link.jpa.model.Country;
import edu.link.jpa.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionService {

    @Autowired
    public RegionDao regionDao;

    @Transactional(readOnly = true)
    public List<Region> readAllRegions(){

        return regionDao.getAllRegions();
    }

    @Transactional(readOnly = true)
    public List<Country> readCountriesByRegionName(String regionName){

        Region region = regionDao.getByName(regionName);

        return region.getCountries();
    }

    @Transactional(readOnly = true)
    public List<Region> getAllRegionsWithJoinFetch(){

        return regionDao.getAllRegionsWithJoinFetch();
    }

}
