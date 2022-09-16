package com.vivi.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vivi.entities.EmployeeEntity;
import com.vivi.services.EmployeeService;

@RestController
@RequestMapping(value="/employees")
public class EmployeeResources {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping
	public ResponseEntity<List<EmployeeEntity>> findAll(){
		List<EmployeeEntity> list=service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Optional<EmployeeEntity>> findById(@PathVariable Long id){
		Optional<EmployeeEntity> employee=service.findById(id);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping
	public ResponseEntity<EmployeeEntity> insert(@RequestBody EmployeeEntity employee){
		EmployeeEntity entity=service.insert(employee);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<EmployeeEntity> update(@PathVariable Long id, @RequestBody EmployeeEntity employee){
		EmployeeEntity entity=service.update(employee, id);
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<EmployeeEntity> deleteById(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
