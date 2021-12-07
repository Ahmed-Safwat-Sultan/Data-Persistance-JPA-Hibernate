package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        //throw new UnsupportedOperationException();
    	Schedule schedule = convertScheduleDTO(scheduleDTO);
    	scheduleService.saveSchedule(schedule);
    	return convertSchedule(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        //throw new UnsupportedOperationException();
    	List<ScheduleDTO> result = new ArrayList<ScheduleDTO>();
    	scheduleService.getAllSchedules().forEach(s -> result.add(convertSchedule(s)));
    	return result;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        //throw new UnsupportedOperationException();
    	List<ScheduleDTO> result = new ArrayList<ScheduleDTO>();
    	scheduleService.getByPetId(petId).forEach(s -> result.add(convertSchedule(s)));
    	return result;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        //throw new UnsupportedOperationException();
    	List<ScheduleDTO> result = new ArrayList<ScheduleDTO>();
    	scheduleService.getByEmployeeId(employeeId).forEach(s -> result.add(convertSchedule(s)));
    	return result;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        //throw new UnsupportedOperationException();
    	List<ScheduleDTO> result = new ArrayList<ScheduleDTO>();
    	scheduleService.getScheduleByCustomerId(customerId).forEach(s -> result.add(convertSchedule(s)));
    	return result;
    }
    
    
    
    
    private ScheduleDTO convertSchedule(Schedule schedule) {
    	ScheduleDTO scheduleDTO = new ScheduleDTO();
		BeanUtils.copyProperties(schedule, scheduleDTO);
		List<Employee> employees = schedule.getEmployees();
		List<Pet> pets = schedule.getPets();
		List<Long> employeeIds = new ArrayList<Long>();
		List<Long> petIds = new ArrayList<Long>();
		if(employees != null) {
			for(Employee e : employees) {
				employeeIds.add(e.getId());
			}
			scheduleDTO.setEmployeeIds(employeeIds);
		}
		if(pets != null) {
			for(Pet p : pets) {
				petIds.add(p.getId());
			}
			scheduleDTO.setPetIds(petIds);
		}
		return scheduleDTO;
	}
	
	private Schedule convertScheduleDTO(ScheduleDTO scheduleDTO) {
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTO, schedule);
		List<Long> employeeIds = scheduleDTO.getEmployeeIds();
		List<Long> petIds = scheduleDTO.getPetIds();
		List<Employee> employees = new ArrayList<Employee>();
		List<Pet> pets = new ArrayList<Pet>();
		if(!employeeIds.isEmpty()) {
			for(Long l : employeeIds) {
				if( employeeService.exsistsById(l)  ){
					employees.add(employeeService.getEmployeeById(l));
				}
			}
			schedule.setEmployees(employees);
		}
		if(!petIds.isEmpty()) {
			for(Long l : petIds) {
				if( petService.exsistsById(l)  ){
					pets.add(petService.getPetById(l));
				}
			}
			schedule.setPets(pets);
		}
		return schedule;
	}
}
