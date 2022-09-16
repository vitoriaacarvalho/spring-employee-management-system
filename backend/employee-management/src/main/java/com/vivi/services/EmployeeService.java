package com.vivi.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivi.entities.EmployeeEntity;
import com.vivi.repositories.EmployeeRepository;
import com.vivi.services.exceptions.ResourceNotFoundException;

@Service
public class EmployeeService {
	@Autowired 
	private EmployeeRepository repo;
	
	public List<EmployeeEntity> findAll() {
		return repo.findAll();
	}
	
	public Optional<EmployeeEntity> findById(Long id) {
		Optional<EmployeeEntity> employee=repo.findById(id);
		return employee;
	}
	
	public EmployeeEntity insert(EmployeeEntity employee) {
		return repo.save(employee);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public EmployeeEntity update(EmployeeEntity employee, Long id) {
		try {
			EmployeeEntity entity=repo.getById(id);
			updateData(entity, employee);
			return repo.save(entity);
			
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(EmployeeEntity entity, EmployeeEntity employee) {
		entity.setFirstName(employee.getFirstName());
		entity.setLastName(employee.getLastName());
		entity.setEmailId(employee.getEmailId());
	}
	
	
}











