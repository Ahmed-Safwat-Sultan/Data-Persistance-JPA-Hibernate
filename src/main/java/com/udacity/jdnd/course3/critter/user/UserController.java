package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        //throw new UnsupportedOperationException();
    	Customer customer = convertCustomerDTO(customerDTO);
    	customerService.saveCustomer(customer);
    	return convertCustomer(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        //throw new UnsupportedOperationException();
    	List<CustomerDTO> result = new ArrayList<CustomerDTO>();
    	customerService.getAllCustomers().forEach(c -> result.add(convertCustomer(c)) );
    	return result;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        //throw new UnsupportedOperationException();
    	Pet pet = petService.getPetById(petId);
    	return convertCustomer(pet.getOwner());
    	
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        //throw new UnsupportedOperationException();
    	Employee employee = convertEmployeeDTO(employeeDTO);
    	employeeService.saveEmployee(employee);
    	return convertEmployee(employee);
    	
    	
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        //throw new UnsupportedOperationException();
    	return convertEmployee(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        //throw new UnsupportedOperationException();
    	Employee e = employeeService.getEmployeeById(employeeId);
    	e.setDaysAvailable(daysAvailable);
    	employeeService.saveEmployee(e);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        //throw new UnsupportedOperationException();
    	DayOfWeek day = employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        List<Employee> employees = employeeService.getEmployeesForService(day,skills);
        List<EmployeeDTO> result = new ArrayList<EmployeeDTO>();
        employees.forEach(e -> result.add(convertEmployee(e)) );
        return result;
    }
    
    
    private CustomerDTO convertCustomer(Customer customer) {
    	CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		List<Pet> pets = customer.getPets();
		List<Long> petsIds = new ArrayList<Long>();
		if(pets != null) {
			for(Pet p : pets) {
				petsIds.add(p.getId());
			}
			customerDTO.setPetIds(petsIds);
		}
		return customerDTO;
	}
	
	private Customer convertCustomerDTO(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		return customer;
	}
	
	private EmployeeDTO convertEmployee(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);
		return employeeDTO;
	}
	
	private Employee convertEmployeeDTO(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		return employee;
	}


}
