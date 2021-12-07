package com.udacity.jdnd.course3.critter.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
	   public List<Schedule> findByPets_Id(Long petId);

	   public List<Schedule> findByEmployees_Id(Long employeeId);

}
