package com.ersspring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ersspring.exception.ApplicationException;
import com.ersspring.exceptions.InvalidLoginException;
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
	
	@PostMapping("employees/login")
	public EmployeePojo getEmployee(@RequestBody EmployeePojo employeePojo) throws ApplicationException,InvalidLoginException {
	return employeeService.findByEmpUserName(employeePojo);	
	}
	
	// http://localhost:7474/api/employees/2
	@GetMapping("employees/{empid}")
	public EmployeePojo empViewInfo(@PathVariable("empid")int empId) throws ApplicationException {
		return employeeService.empViewInfo(empId);
	}
	
	// http://localhost:7474/api/employees/2
	@PutMapping("employees")
	public EmployeePojo empUpdateInfo(@RequestBody EmployeePojo employeePojo) throws ApplicationException {
		return employeeService.empUpdateInfo(employeePojo);
	}
	
	// http://localhost:7474/api/employees
	@GetMapping("employees")	
	public List<EmployeePojo> manViewAll() throws ApplicationException {
		return employeeService.manViewAll();
	}
	
	
	
}
