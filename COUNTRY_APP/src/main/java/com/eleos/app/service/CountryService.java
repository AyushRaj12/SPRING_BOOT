package com.eleos.app.service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleos.app.controller.NoDataFoundException;
import com.eleos.app.controller.ResourceNotFoundException;
import com.eleos.app.controller.ValidationException;

import com.eleos.app.db.Country;


@Service
public class CountryService{
	
	@Autowired
	private CountryRepository countryRepository;
	
	
    public List<Country> getAllCountriesFromDatabase(int page,int pageSize) {
    	
        List<Country> allCountries = new ArrayList<>();
        
        countryRepository.findAll().forEach(allCountries::add);
        if (allCountries.isEmpty()) {
            throw new NoDataFoundException("No resources found.");
        }
        allCountries.sort(Comparator.comparing(Country::getName));
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, allCountries.size());
        List<Country> Countries = allCountries.subList(startIndex, endIndex);
        return Countries;
    }


	public void addCountry(Country country){
	  
		 if (!isValidResource(country)) {
		        throw new ValidationException("Resource validation failed");
		    }
		 
		 countryRepository.save(country);
	}

	private boolean isValidResource(Country country) {
		
		if(country.getName()!="") {
			return true;
		}
		return false;
	}


	public Country getCountry(Integer id) {
		    Country resource = countryRepository.findById(id).orElse(null);
		    if (resource == null) {
		        throw new ResourceNotFoundException("Resource not found with ID: " + id);
		    }
		   
		    return resource;
	}


	public void updateCountry(Integer id, Country country) {
		Country existingCountry = countryRepository.findById(id).orElse(null);
        if (existingCountry == null) {
        	 throw new ResourceNotFoundException("Resource not found with ID: " + id);
        }
        else {
        	existingCountry.setName(country.getName());
            countryRepository.save(existingCountry);
        }
	}


	public void deleteCountry(Integer id) {
		
		 if (!countryRepository.existsById(id)) {
		        throw new ResourceNotFoundException("Resource not found with ID: " + id);
		    }
		 countryRepository.deleteById(id);
	} 
}
