package com.wtw.test.restserviceproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtw.test.restserviceproject.beans.EmployeeWrapper;
import com.wtw.test.restserviceproject.exception.RecordNotFoundException;
import com.wtw.test.restserviceproject.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/saveEmployee")
	public ResponseEntity<EmployeeWrapper> saveEmployee(@RequestBody EmployeeWrapper employeeWrapper) {
		
		employeeWrapper = employeeService.saveEmployee(employeeWrapper);
		return ResponseEntity.ok(employeeWrapper);
	}
	
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<EmployeeWrapper> getEmployeeById(@PathVariable Long id) throws RecordNotFoundException{
		
		EmployeeWrapper employeeWrapper = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employeeWrapper);
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<EmployeeWrapper> updateEmployee(@RequestBody EmployeeWrapper employeeWrapper) throws RecordNotFoundException{
		
		employeeWrapper = employeeService.updateEmployeeDetails(employeeWrapper);
		return ResponseEntity.ok(employeeWrapper);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<EmployeeWrapper> deleteEmployeeById(@PathVariable Long id) throws RecordNotFoundException{
		
		employeeService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
