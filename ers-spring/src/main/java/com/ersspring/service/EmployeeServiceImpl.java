package com.ersspring.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersspring.dao.EmployeeDao;
import com.ersspring.entity.EmployeeEntity;
import com.ersspring.exception.ApplicationException;
import com.ersspring.exceptions.InvalidLoginException;
import com.ersspring.pojo.EmployeePojo;
import com.ersspring.pojo.RolesPojo;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		
	}
	@Override
	public String hashPassword(String password) {
		// takes your password and returns an encrypted version of it
		String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));

		return hashedPass;
	}

	// checks the users password
	@Override
	public boolean checkPass(String password, String hashedPass) {
		// takes your password and an encrypted password and compares it to see if its
		// the same values
		// as the password, if so it returns true
		if (BCrypt.checkpw(password, hashedPass)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public EmployeePojo findByEmpUserName(EmployeePojo employeePojo) throws InvalidLoginException{
		EmployeePojo user=null;
		RolesPojo rolePojo = new RolesPojo();
		
		EmployeeEntity fetchedEmpEnt = employeeDao.findByEmpUserName(employeePojo.getEmpUserName());
		if (fetchedEmpEnt == null){
			throw new InvalidLoginException("Invalid login or password");
		}
		Boolean checkedPass = checkPass(employeePojo.getEmpHashedPassword(), fetchedEmpEnt.getEmpHashedPassword());
		if (checkedPass == true) {
			rolePojo.setRoleId(fetchedEmpEnt.getRolesEntity().getRoleId());
			rolePojo.setRole(fetchedEmpEnt.getRolesEntity().getRole());
			
			user= new EmployeePojo(fetchedEmpEnt.getEmpId(),fetchedEmpEnt.getEmpFirstName(),fetchedEmpEnt.getEmpLastName(),
					fetchedEmpEnt.getEmpUserName(),fetchedEmpEnt.getEmpHashedPassword(),rolePojo);

			return user;
		}else { 
			throw new InvalidLoginException("Invalid login or password");
		}
		
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
	
}

