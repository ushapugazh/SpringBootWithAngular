package com.sb.ms.ang.empdetails.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sb.ms.ang.empdetails.model.Employee;
import com.sb.ms.ang.empdetails.service.ServiceInterface;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private ServiceInterface empService;

	public EmployeeController() {

	}

//	@Value("${message}")
	private String welcomeMessage;

	/*
	 * @Value("${user.role}") private String userRole;
	 */

	public EmployeeController(ServiceInterface empService) {
		this.empService = empService;
	}

	@GetMapping
	public List<Employee> findAll() {
		return empService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Employee> findById(@PathVariable Integer id) {
		return empService.findById(id);
	}

	// create a book
	@ResponseStatus(HttpStatus.CREATED) // 201
	@PostMapping
	public Employee create(@RequestBody Employee emp) {
		return empService.save(emp);
	}

	// update a book
	@PutMapping
	public Employee update(@RequestBody Employee emp) {
		return empService.save(emp);
	}

	// delete a book
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		empService.deleteById(id);
	}

	@GetMapping("/config")
	public String getConfig() {
		return String.format( welcomeMessage);
	}

}
