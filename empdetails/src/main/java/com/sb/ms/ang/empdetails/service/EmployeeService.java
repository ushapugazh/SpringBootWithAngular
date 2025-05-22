package com.sb.ms.ang.empdetails.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.ms.ang.empdetails.model.Employee;
import com.sb.ms.ang.empdetails.repo.EmployeeRepository;
import com.sb.ms.ang.empdetails.utility.KafkaUtil;

@Service
public class EmployeeService implements  ServiceInterface{
	
	@Autowired
    private EmployeeRepository empRepo;

    public List<Employee> findAll() {
        return empRepo.findAll();
    }

    public Optional<Employee> findById(Integer id) {
        return empRepo.findById(id);
    }

    public Employee save(Employee emp) {
    	Employee emp1 =	empRepo.save(emp);
    	if(emp1 != null  ) {
    		System.out.println("Let us Put the Message in the Kafka so that user EMaail is Triggererd");
    		KafkaUtil ku = new KafkaUtil();
    		try {
    		long kafakOffsetID = ku.sendKafkaMsg(emp.toString());
    		System.out.println("Message Successfully sent to Kafka and teh offset Id is : " +kafakOffsetID);
    		}
    		catch(Exception ex) {
    			System.out.println("Message not sent to Kafka");
    		}
    		
    	}
        return emp1;
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


