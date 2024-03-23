package com.eleos.app.controller;

import com.eleos.app.db.Country;
import com.eleos.app.service.CountryService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CountryController {
   
	@Autowired
	private CountryService countryService;

	//Get Mapping to get the countries
	@RequestMapping("/api/countries")
	@GetMapping
    public List<Country> getAllCountries(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize) {
    
      return countryService.getAllCountriesFromDatabase(page,pageSize);
        
    }
	
	//Post mapping to add the Countries
	@RequestMapping(method=RequestMethod.POST,value="/api/countries")
	public void addCountry(@RequestBody Country country) {
	     countryService.addCountry(country);
	}
    
	//Get mapping to get countries based on id
	@RequestMapping("/api/country/{id}")
	public Country getCountry(@PathVariable Integer id)
	{
		return countryService.getCountry(id);
	}
	
	//Put mapping to update the country name for particular id
	@RequestMapping(method=RequestMethod.PUT,value="/api/country/{id}")
	public void updateCountry(@RequestBody Country country,@PathVariable Integer id) {
		countryService.updateCountry(id,country);
	}
	
	//Delete country name with given id
	@RequestMapping(method=RequestMethod.DELETE,value="/api/country/{id}")
	public void deleteCountry(@PathVariable Integer id)
	{
            countryService.deleteCountry(id);
	}
   
}
