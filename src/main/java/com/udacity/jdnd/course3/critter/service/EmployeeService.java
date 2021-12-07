package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Transactional
@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public Employee saveEmployee(Employee e) {
		return employeeRepo.save(e);
	}
	
	
	public Employee getEmployeeById(Long id) {
		return employeeRepo.findById(id).get();
	}
	
	 public List<Employee> getEmployeesForService(DayOfWeek  daysAvailable, Set<EmployeeSkill> skills) {
	        List<Employee> employees = employeeRepo.findEmployeesByDaysAvailableAndSkillsIn(daysAvailable, skills);
	        List<Employee> availableEmployees = new ArrayList<>();
	         for(Employee e : employees){
	            // Check if employee skills contains the required skills
	            if(e.getSkills().containsAll(skills)) {
	                availableEmployees.add(e);
	            }
	        }
	        return availableEmployees;
	    }
	
	public boolean exsistsById(Long id) {
		return employeeRepo.existsById(id);
	}
	
}
