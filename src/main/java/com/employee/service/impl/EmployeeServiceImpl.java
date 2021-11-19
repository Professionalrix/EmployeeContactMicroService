package com.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	@Autowired
	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		
		return  employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Employee employee= null;
		try {
			List<Employee> list = getAllEmployee();
			employee = list.stream().filter(e->e.getId()==id).findFirst().get();
				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee, Long id) {
		
		 if(employeeRepository.findById(id).isPresent()) {
			 Employee emp= employeeRepository.findById(id).get();
			 emp.setDesignation(employee.getDesignation());
			 emp.setFirstName(employee.getFirstName());
			 emp.setLastName(employee.getLastName());
			 employeeRepository.save(emp);
			 return emp;
		 }
		return null;
	}

	@Override
	public void deleteEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id).get();
		employeeRepository.delete(employee);
		
	}

}
