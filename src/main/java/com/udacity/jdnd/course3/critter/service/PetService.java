package com.udacity.jdnd.course3.critter.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Transactional
@Service
public class PetService {
	
	@Autowired
	PetRepository petRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	public Pet savePet(Pet p) {
		if (customerRepo.existsById(p.getOwnerId()) ) {
			
			Customer owner = customerRepo.findById(p.getOwnerId()).get();
			p.setOwner(owner);
			owner.addPet(p);
			 
		}
		return petRepo.save(p);
	}
	
	
	public Pet getPetById(Long id) {
		return petRepo.findById(id).get();
	}
	
	
	public List<Pet> getAllPets(){
		return petRepo.findAll();
	}
	
	
	public Pet saveOwnerForPet(Pet pet) {
        Pet savedPet = petRepo.save(pet);
        Customer owner = savedPet.getOwner();
        owner.getPets().add(savedPet);
        customerRepo.save(owner);
        return savedPet;
    }
	
	 public List<Pet> getPetsByOwnerId(Long ownerId){
	        Customer customer = customerRepo.getOne(ownerId);
	        return customer.getPets();
	    }
	 
	 public boolean exsistsById(Long id) {
		 return petRepo.existsById(id);
	 }
}
