package com.ersspring.service;

import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersspring.dao.EmployeeDao;
import com.ersspring.entity.EmployeeEntity;
import com.ersspring.exception.ApplicationException;
import com.ersspring.pojo.EmployeePojo;
import com.ersspring.pojo.RolesPojo;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		
	}

	@Override
	public EmployeePojo getEmployee(EmployeePojo employeePojo) throws ApplicationException {
		// TO BE IMPLEMENTED
		return null;
	}

	@Override
	public EmployeePojo empViewInfo(int empId) throws ApplicationException {
		Optional<EmployeeEntity> employeeEntityOpt = employeeDao.findById(empId);
		EmployeePojo employeePojo = null;
		if(employeeEntityOpt.isPresent()) {
			// take out the entity object which is wrapped into the optional object
			EmployeeEntity fetchedEmployeeEntity = employeeEntityOpt.get();
			// copy the entity into the pojo
			employeePojo = new EmployeePojo();
			BeanUtils.copyProperties(fetchedEmployeeEntity, employeePojo); 
		}
		return employeePojo;
	}

	@Override
	public EmployeePojo empUpdateInfo(EmployeePojo employeePojo, int empId) throws ApplicationException {
		// copy the pojo into an entity object
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeePojo, employeeEntity);
				
		//  now pass the employeeEntity object to spring data jpa to be updated into the table
		EmployeeEntity returnedEmployeeEntity = employeeDao.save(employeeEntity);
				
		return employeePojo;
	}

	@Override
	public List<EmployeePojo> manViewAll() throws ApplicationException {
		List<EmployeeEntity> allEmployeesEntity = employeeDao.findAll();
		// now we have to copy each employee entity object in the collection to a collection on employee pojo
		// create a empty collection of employee pojo
		List<EmployeePojo> allEmployeesPojo = new ArrayList<EmployeePojo>();
		EmployeeEntity getEmployeeEntity = null;
		
		RolesPojo rolePojo = new RolesPojo();
		rolePojo.setRoleId(getEmployeeEntity.getRolesEntity().getRoleId());
		rolePojo.setRole(getEmployeeEntity.getRolesEntity().getRole());
		
		for(EmployeeEntity fetchedEmployeeEntity: allEmployeesEntity) {
			EmployeePojo returnEmployeePojo = new EmployeePojo(fetchedEmployeeEntity.getEmpId(),fetchedEmployeeEntity.getEmpFirstName(),
					fetchedEmployeeEntity.getEmpLastName(),fetchedEmployeeEntity.getEmpUserName(),
					fetchedEmployeeEntity.getEmpHashedPassword(), rolePojo);
			allEmployeesPojo.add(returnEmployeePojo);
		}
		return allEmployeesPojo;
	}

	@Override
	public EmployeePojo findByEmpUserNameAndEmpHashedPassword(String username, String password) {
		EmployeePojo employeePojo=null;
		RolesPojo rolePojo = new RolesPojo();
		
		EmployeeEntity fetchedEmpEnt = employeeDao.findByEmpUserNameAndEmpHashedPassword(username, password);
		
		rolePojo.setRoleId(fetchedEmpEnt.getRolesEntity().getRoleId());
		rolePojo.setRole(fetchedEmpEnt.getRolesEntity().getRole());
		
		employeePojo= new EmployeePojo(fetchedEmpEnt.getEmpId(),fetchedEmpEnt.getEmpFirstName(),fetchedEmpEnt.getEmpLastName(),
				fetchedEmpEnt.getEmpUserName(),fetchedEmpEnt.getEmpHashedPassword(),rolePojo);

		return employeePojo;
	}
}

