package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.entity.Cat;

public interface CatRepository extends JpaRepository<Cat, Long> {

}
