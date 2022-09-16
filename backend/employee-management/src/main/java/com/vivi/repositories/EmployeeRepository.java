package com.vivi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivi.entities.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	
}
