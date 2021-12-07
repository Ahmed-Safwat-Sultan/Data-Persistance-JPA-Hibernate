package com.udacity.jdnd.course3.critter.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cat extends Pet {
	
	
	@Column(length = 20)
	String species;

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
}
