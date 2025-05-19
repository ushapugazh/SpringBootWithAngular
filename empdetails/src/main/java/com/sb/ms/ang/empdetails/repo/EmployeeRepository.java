package com.sb.ms.ang.empdetails.repo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sb.ms.ang.empdetails.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByDesignation(String designation);

	/*
	 * // Custom query
	 * 
	 * @Query("SELECT e FROM employee e WHERE e.doj > :date") List<Employee>
	 * findByPublishedDateAfter(@Param("date") LocalDate date);
	 */
}
