package com.wtw.test.restserviceproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtw.test.restserviceproject.beans.EmployeeWrapper;
import com.wtw.test.restserviceproject.exception.RecordNotFoundException;
import com.wtw.test.restserviceproject.model.Employee;
import com.wtw.test.restserviceproject.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;

	public EmployeeWrapper saveEmployee(EmployeeWrapper inputEmployee) {
		
		Employee employee = new Employee();
		employee.setName(inputEmployee.getName());
		employee.setAddress(inputEmployee.getAddress());
		employee.setRole(inputEmployee.getRole());
		employee.setSalary(inputEmployee.getSalary());
		
		employee = repository.save(employee);
		
		inputEmployee.setId(employee.getId());
		inputEmployee.setName(employee.getName());
		inputEmployee.setAddress(employee.getAddress());
		inputEmployee.setRole(employee.getRole());
		inputEmployee.setSalary(employee.getSalary());
		return inputEmployee;
		
	}

	public EmployeeWrapper getEmployeeById(Long id) throws RecordNotFoundException {
		
		EmployeeWrapper employeeWrapperOutput = null;
		
		Optional<Employee> employeeOptionalData = repository.findById(id);
		
		if (employeeOptionalData.isPresent()) {
			employeeWrapperOutput = new EmployeeWrapper();
			Employee employee = employeeOptionalData.get();
			employeeWrapperOutput.setId(employee.getId());
			employeeWrapperOutput.setName(employee.getName());
			employeeWrapperOutput.setAddress(employee.getAddress());
			employeeWrapperOutput.setRole(employee.getRole());
			employeeWrapperOutput.setSalary(employee.getSalary());
		}
		else {
			throw new RecordNotFoundException("Employee record doesn't exist.");
		}
		return employeeWrapperOutput;
	}
	
	public EmployeeWrapper updateEmployeeDetails(EmployeeWrapper inputEmployee) throws RecordNotFoundException {
		
		Optional<Employee> employeeOptionalData = repository.findById(inputEmployee.getId());
		
		if (employeeOptionalData.isPresent()) {
			Employee employee = employeeOptionalData.get();
			
			// Update the details
			employee.setName(inputEmployee.getName());
			employee.setAddress(inputEmployee.getAddress());
			employee.setRole(inputEmployee.getRole());
			employee.setSalary(inputEmployee.getSalary());
			repository.save(employee);
			
			// Populate the updated details in the output object
			EmployeeWrapper employeeWrapper = new EmployeeWrapper();
			employeeWrapper.setId(employee.getId());
			employeeWrapper.setName(employee.getName());
			employeeWrapper.setAddress(employee.getAddress());
			employeeWrapper.setRole(employee.getRole());
			employeeWrapper.setSalary(employee.getSalary());
			return employeeWrapper;
		}
		else {
			throw new RecordNotFoundException("Employee record doesn't exist.");
		}
	}
	
	public void deleteById(Long id) throws RecordNotFoundException{
		
		Optional<Employee> employeeOptionalData = repository.findById(id);
		
		if (employeeOptionalData.isPresent()) {
			Employee employee = employeeOptionalData.get();
			repository.delete(employee);
		}else {
			throw new RecordNotFoundException("Employee record doesn't exist.");
		}
	}
}
	
	
	
