package com.jbk.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jbk.exception.ResourceNotFoundException;
import com.jbk.model.Employee;
import com.jbk.repository.EmployeeRepository;
import com.jbk.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {

//		Optional<Employee> employee =  employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "id", id);
//		}

		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {

		// First we have to check whether employee with given id is exist in DB or not
		Employee oldEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		oldEmployee.setFirstName(employee.getFirstName());
		oldEmployee.setLastName(employee.getLastName());
		oldEmployee.setEmail(employee.getEmail());
		// save existing employee into DB

		employeeRepository.save(oldEmployee);
		return oldEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		// First we have to check whether employee with given id is exist in DB or not
		employeeRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Employee", "id", id));
		employeeRepository.deleteById(id);
	}

}
