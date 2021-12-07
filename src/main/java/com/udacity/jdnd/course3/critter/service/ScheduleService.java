package com.udacity.jdnd.course3.critter.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;

@Transactional
@Service
public class ScheduleService {
	
	@Autowired
	ScheduleRepository scheduleRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public Schedule saveSchedule(Schedule s) {
//		if( !s.getEmployees().isEmpty()) {
//			for(Employee e : s.getEmployees()) {
//				if (employeeRepo.existsById(e.getId()) ) {
//				
//				Employee employee = employeeRepo.findById(e.getId()).get();
//				p.setOwner(owner);
//				owner.addPet(p);
//				 
//				}
//			}
//		}
		return scheduleRepo.save(s);
	}
	
	public Schedule getById(Long id) {
		return scheduleRepo.findById(id).get();
	}
	
	public List<Schedule> getAllSchedules(){
		return scheduleRepo.findAll();
	}
	
	public List<Schedule> getByPetId(Long id) {
		return scheduleRepo.findByPets_Id(id);
	}
	
	public List<Schedule> getByEmployeeId(Long id) {
		return scheduleRepo.findByEmployees_Id(id);
	}
	
	 public List<Schedule> getScheduleByCustomerId(Long customerId) {
	       	Customer customer = customerRepo.findById(customerId).get();
	        List<Schedule> schedules = new ArrayList<>();
	        List<Pet> pets = customer.getPets();
	        for (Pet p : pets) {
	            schedules.addAll(scheduleRepo.findByPets_Id(p.getId()));
	        }
	        return schedules;
	    }

}
