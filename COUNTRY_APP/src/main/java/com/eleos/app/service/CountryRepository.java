package com.eleos.app.service;

import org.springframework.data.repository.CrudRepository;

import com.eleos.app.db.Country;

public interface CountryRepository extends CrudRepository<Country,Integer> {

	//Object findAll = null;

}
