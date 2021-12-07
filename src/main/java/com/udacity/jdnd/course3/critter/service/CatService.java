package com.udacity.jdnd.course3.critter.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.udacity.jdnd.course3.critter.entity.Cat;
import com.udacity.jdnd.course3.critter.repository.CatRepository;

@Transactional
public class CatService {
	
	@Autowired
	CatRepository catRepo;
	
	public Cat saveCat(Cat cat) {
		return catRepo.save(cat);
	}
}
