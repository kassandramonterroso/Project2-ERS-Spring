package com.ersspring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ersspring.exception.ApplicationException;
import com.ersspring.pojo.EmployeePojo;
import com.ersspring.service.EmployeeService;


@CrossOrigin // to enable cors
@RestController
@RequestMapping("api")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	// create the rest methods for the rest enpoints
	
	// http://localhost:5555/api/employees
	
	@GetMapping("employees")
	public EmployeePojo getEmployee(EmployeePojo employeePojo) throws ApplicationException {
		return employeeService.getEmployee(employeePojo);
	}
	
	// http://localhost:5555/api/employees/2
	@GetMapping("employees/{empid}")
	public EmployeePojo empViewInfo(int empId) throws ApplicationException {
		return employeeService.empViewInfo(empId);
	}
	
	// http://localhost:5555/api/employees/2
	@PutMapping("employees/{empid}")
	public EmployeePojo empUpdateInfo(EmployeePojo employeePojo, int empId) throws ApplicationException {
		return employeeService.empUpdateInfo(employeePojo, empId);
	}
	
	@GetMapping("employees")	
	public List<EmployeePojo> manViewAll() throws ApplicationException {
		return employeeService.manViewAll();
	}
	
	
	
}
