package com.shivani.employeeManagementSystem.service;

import java.util.List;
import java.util.Optional;

import com.shivani.employeeManagementSystem.entity.Employee;

public interface EmployeeService {
	
	//Here...we create methods to perform all CRUD operations
	
	public Employee addEmp(Employee emp);
	
	public String removeEmp(int id);
	
	public Optional<Employee> findEmp(int id);
	
	public String updateEmp(int id);
	
	public List<Employee> allEmp();
}
