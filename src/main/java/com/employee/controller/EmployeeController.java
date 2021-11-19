package com.employee.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
//@RequestMapping("/")
public class EmployeeController {


		@Autowired
		private EmployeeService employeeService;
		
		@PostMapping("/employees/add") 
		public ResponseEntity<Employee>addEmployee(@RequestBody Employee employee) { 
		Employee emp =  null;
		try {
			emp = employeeService.createEmployee(employee);
			return ResponseEntity.of(Optional.of(emp));
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	 }
				
		//get all Employees
		
		@GetMapping("/employees")
		public ResponseEntity<List<Employee>>  getEmployees()
		{
				List<Employee> allEmployee = employeeService.getAllEmployee();
				if(allEmployee.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
				}
				
				return ResponseEntity.of(Optional.of(allEmployee));
		}
		
		// get employee by id
		@GetMapping("/employees/{id}")
		public ResponseEntity<Employee>  getEmployee(@PathVariable("id")Long id)
		{
				Employee employee = employeeService.getEmployeeById(id);
				if(employee==null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
				}
				
				return ResponseEntity.of(Optional.of(employee));
		
		}
		
		@PutMapping("/employees/{id}")
		public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id")Long id)
		{
			try {
				Employee updateEmployee = this.employeeService.updateEmployee(employee, id);
			
			if(updateEmployee!=null) {
				return ResponseEntity.ok().body(employee);
			}
			
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
			}
			
			}catch (Exception e) {
				e.printStackTrace( );
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		
		}
		
		
		//delete employee
		
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<?> deleteEmployee(@PathVariable("id")Long id)
		{
				try {
					this.employeeService.deleteEmployeeById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
				
		}
		
		
		
		
		
}
