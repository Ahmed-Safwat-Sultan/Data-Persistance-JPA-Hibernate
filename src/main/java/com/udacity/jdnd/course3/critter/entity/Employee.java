package com.udacity.jdnd.course3.critter.entity;

import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length = 20)
	private String name;
	
	@ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
	private Set<EmployeeSkill> skills;
	
	@ElementCollection(targetClass =  DayOfWeek.class)
    @Enumerated(EnumType.STRING)
	private Set<DayOfWeek> daysAvailable;
	
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<EmployeeSkill> getSkills() {
		return skills;
	}
	public void setSkills(Set<EmployeeSkill> skills) {
		this.skills = skills;
	}
	public Set<DayOfWeek> getDaysAvailable() {
		return daysAvailable;
	}
	public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
		this.daysAvailable = daysAvailable;
	}
	
	
	public boolean isDayAvailable() {
		return true;//for now
	}

}
