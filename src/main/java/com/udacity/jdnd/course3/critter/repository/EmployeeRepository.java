package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	public List<Employee> findEmployeesByDaysAvailableAndSkillsIn(DayOfWeek  daysAvailable, Set<EmployeeSkill> skills);

}
