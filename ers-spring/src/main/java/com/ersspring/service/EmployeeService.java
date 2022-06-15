package com.ersspring.service;

import java.util.List;

import com.ersspring.exception.ApplicationException;
import com.ersspring.pojo.EmployeePojo;

public interface EmployeeService {

	// method to get employee
	EmployeePojo getEmployee(EmployeePojo employeePojo) throws ApplicationException;

	// Method for employee to view their information
	EmployeePojo empViewInfo(int empId) throws ApplicationException;

	// Method for employee to update their information
	EmployeePojo empUpdateInfo(EmployeePojo employeePojo, int empId) throws ApplicationException;

	// Method for manager to view all employees
	List<EmployeePojo> manViewAll() throws ApplicationException;
	
	//hashes password
	public String hashPassword(String password);
	//checks hashed password
	public boolean checkPass(String password, String hashedPass);
	
	

}
