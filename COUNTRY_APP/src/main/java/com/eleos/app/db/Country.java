package com.eleos.app.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Column(nullable = false, unique = true)
    private String name;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		 this.name = name;
	}
	
}
