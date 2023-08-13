package com.shivani.employeeManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivani.employeeManagementSystem.entity.Employee;
import com.shivani.employeeManagementSystem.repository.EmployeeRepository;

@Service	//To specify this class as a service class(used to write business logics)
public class EmployeeServiceImplementation implements EmployeeService{

	@Autowired		//So that...Spring IOC container will perform automatic dependency injection
	private EmployeeRepository empRepo;		//Creating ref. of repo interface...so that we can use all methods of JpaRepository class.
	
	@Override
	public Employee addEmp(Employee emp) {
		Employee added_emp = empRepo.save(emp);		//save() is an inbuild method from JpaRepository
		return added_emp;
	}

	@Override
	public String removeEmp(int id) {
		empRepo.deleteById(id);
		return "Deleted data successfully";
	}

	@Override
	public Optional<Employee> findEmp(int id) {
		Optional<Employee> found_emp = empRepo.findById(id);	//Optional<> because found_emp can get 'null' value also.
		if(found_emp.isPresent())
		{
			return found_emp;
		}
		return null;
	}

	@Override
	public String updateEmp(int id) {
		Optional<Employee> emp = empRepo.findById(id);
		if(emp.isPresent())
		{
			Employee emp_found = new Employee();
			empRepo.save(emp_found);
			return "Employee with id " + id + " updated successfully";
		}
		else
		{
			return "Employee with id " + id + " not found";
		}
	}

	@Override
	public List<Employee> allEmp() {
		List<Employee> all_emp = empRepo.findAll();
		return all_emp;
	}

}
