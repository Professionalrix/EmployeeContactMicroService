package com.employee.service;

import java.util.List;

import com.employee.model.Employee;

public interface EmployeeService {

	public Employee createEmployee(Employee employee);
	public List<Employee> getAllEmployee();
	public Employee getEmployeeById(Long id);
	public Employee updateEmployee(Employee employee,Long id);
	public void deleteEmployeeById(Long id);
	
}
