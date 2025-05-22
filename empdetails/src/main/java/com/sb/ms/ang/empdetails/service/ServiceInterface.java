package com.sb.ms.ang.empdetails.service;

import java.util.List;
import java.util.Optional;

import com.sb.ms.ang.empdetails.model.Employee;

public interface ServiceInterface {
	 public List<Employee> findAll();
	 public Optional<Employee> findById(Integer id) ;
	 public Employee save(Employee emp) ;
	 public void deleteById(Integer id) ;
	 public List<Employee> findByTitle(String designation);
	 
	 
}
