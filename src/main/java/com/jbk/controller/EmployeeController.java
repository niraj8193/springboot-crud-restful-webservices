package com.jbk.controller;

import java.util.List;

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

import com.jbk.model.Employee;
import com.jbk.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	// Build Create API

	@PostMapping("/saveemp")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);

	}

	// Fetch all Employee API

	@GetMapping("/getall")
	public List<Employee> getAllEmpoyee() {
		return employeeService.getAllEmployee();
	}

	// Fetch Employee BY ID Rest API

	@GetMapping("/getempbyid/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {

		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);

	}

//Update employee api
	@PutMapping("updateemp/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);

	}

	// Delete Employee API
	@DeleteMapping("/deleteemp/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
	}

}
