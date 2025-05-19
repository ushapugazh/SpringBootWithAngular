package com.sb.ms.ang.empdetails.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.ms.ang.empdetails.model.Employee;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello Usha Welcome");
    }
    
    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
	public List<Employee> firstPage() {
    	 List<Employee> emp = createList();
    	return emp;
	}
	
	private static List<Employee> createList() {
		List<Employee> tempEmployees = new ArrayList<>();
		Employee emp1 = new Employee();
		emp1.setName("Usha Josphine");
		emp1.setDesignation("Trainer");
	//	emp1.setEmpId("1");
		emp1.setSalary(3000);

		Employee emp2 = new Employee();
		emp2.setName("UJsha Pugazh");
		emp2.setDesignation("Lead developer");
	//	emp2.setEmpId("2");
		emp2.setSalary(3000);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);
		return tempEmployees;
	}
}