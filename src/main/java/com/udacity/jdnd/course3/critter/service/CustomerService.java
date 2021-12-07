package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;

@Transactional
@Service
public class CustomerService {
	
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	PetRepository petRepo;
	
	public Customer saveCustomer(Customer c) {
		return customerRepo.save(c);
	}
	
	public List<Customer> getAllCustomers(){
		return customerRepo.findAll();
	}
	
	public Customer getById(Long id) {
		return customerRepo.findById(id).get();
	}
	
	public Customer findByPet(Long petId){
        return petRepo.findById(petId).get().getOwner();
    }
	
	public void savePetForCustomer(Long id, Pet pet){
        Customer c =customerRepo.findById(id).get();
        pet.setOwner(c);
        c.getPets().add(pet);
    }
}
