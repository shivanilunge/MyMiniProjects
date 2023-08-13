package com.shivani.employeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivani.employeeManagementSystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>	// <PojoClassName, TypeOfId> 
{

}
