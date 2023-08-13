package com.shivani.employeeManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivani.employeeManagementSystem.entity.Employee;
import com.shivani.employeeManagementSystem.repository.EmployeeRepository;
import com.shivani.employeeManagementSystem.service.EmployeeService;
import com.shivani.employeeManagementSystem.service.EmployeeServiceImplementation;

//Controller class is used to accept all requests from server

@RestController		//To specify that it is a controller class
@RequestMapping("/emp")		//optional
public class EmployeeController {
	
	//creating refVar of service class
	@Autowired
	private EmployeeServiceImplementation empService;
	
	//Here we are creating all REST APIs
	
	@GetMapping("/home")
	public String home()
	{
		return "Welcome To Employee Managenent System Application";
	}
	
	@PostMapping("/addEmp")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee emp) //@RequestBody to accept object
	{
		Employee emp_added = empService.addEmp(emp);
		return new ResponseEntity<Employee>(emp_added,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeEmp/{id}") // {id} to accept id value on HTTP URL
	public ResponseEntity<String> removeEmp(@PathVariable int id) //@PathVariable to accept any value
	{
		empService.removeEmp(id);
		return new ResponseEntity<String>("Removed Successfully",HttpStatus.ACCEPTED);	
	}
	
	@GetMapping("/findEmp/{id}")
	public ResponseEntity<Optional<Employee>> findEmp(@PathVariable int id)
	{
		Optional<Employee> emp_found = empService.findEmp(id);
		return new ResponseEntity<Optional<Employee>>(emp_found,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<String> updateEmp(@PathVariable int id)
	{
		empService.updateEmp(id);
		return new ResponseEntity<String>("Updated Successfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allEmp")
	public ResponseEntity<List<Employee>> allEmp()
	{
		List<Employee> all_emp = empService.allEmp();
		return new ResponseEntity<List<Employee>>(all_emp,HttpStatus.ACCEPTED);
		
	}
}