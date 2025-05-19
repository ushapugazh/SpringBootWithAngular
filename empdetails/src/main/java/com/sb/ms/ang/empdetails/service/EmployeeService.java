package com.sb.ms.ang.empdetails.service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.ms.ang.empdetails.model.Employee;
import com.sb.ms.ang.empdetails.repo.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepository empRepo;

    public List<Employee> findAll() {
        return empRepo.findAll();
    }

    public Optional<Employee> findById(Integer id) {
        return empRepo.findById(id);
    }

    public Employee save(Employee emp) {
        return empRepo.save(emp);
    }

    public void deleteById(Integer id) {
    	empRepo.deleteById(id);
    }

    public List<Employee> findByTitle(String designation) {
        return empRepo.findByDesignation(designation);
    }

	/*
	 * public List<Employee> findByPublishedDateAfter(LocalDate date) { //return
	 * empRepo.findByPublishedDateAfter(date); return new List<Employee>(); }
	 */
}


