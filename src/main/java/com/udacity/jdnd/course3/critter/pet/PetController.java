package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CatService;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
	
	
	@Autowired
	PetService petService;
	
	@Autowired
	CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        //throw new UnsupportedOperationException();
    	Pet pet = convertPetDTO(petDTO);
    	petService.savePet(pet);
    	return convertPet(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        //throw new UnsupportedOperationException();
    	Pet pet = petService.getPetById(petId);
    	return convertPet(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        //throw new UnsupportedOperationException();
    	List<PetDTO> result = new ArrayList<PetDTO>();
    	petService.getAllPets().forEach(p -> result.add(convertPet(p)));
    	return result;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        //throw new UnsupportedOperationException();
    	List<PetDTO> result = new ArrayList<PetDTO>();
    	customerService.getById(ownerId).getPets().forEach(p -> result.add(convertPet(p)));
    	return result;
    }
    
    
    
    private PetDTO convertPet(Pet pet) {
    	PetDTO petDTO = new PetDTO();
		BeanUtils.copyProperties(pet, petDTO);
		return petDTO;
	}
	
	private Pet convertPetDTO(PetDTO petDTO) {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDTO, pet);
		return pet;
	}

}
