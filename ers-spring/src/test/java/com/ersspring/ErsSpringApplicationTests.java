package com.ersspring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ersspring.dao.EmployeeDao;
import com.ersspring.entity.EmployeeEntity;
import com.ersspring.entity.RolesEntity;
import com.ersspring.exception.ApplicationException;
import com.ersspring.exceptions.InvalidLoginException;
import com.ersspring.pojo.EmployeePojo;
import com.ersspring.pojo.RolesPojo;
import com.ersspring.service.EmployeeServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ErsSpringApplicationTests {
	
	@InjectMocks
	EmployeeServiceImpl service;
	@Mock
	EmployeeDao dao;
	@Mock 
	EmployeeServiceImpl mockService;
	private RolesEntity userRole = new RolesEntity(1, "employee");
	private RolesPojo userRolePojo = new RolesPojo(1, "employee");
	private EmployeeEntity testUser = new EmployeeEntity(1,"testFirstName", "testLastName","testUserName"," $2a$10$HwO.e2gax/jJuW49MfLbvujyUQu8Wr6yppRHXFkLp11./Hnaj74Nu", userRole);
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testFindUserByUserName() throws InvalidLoginException {
		EmployeePojo testInput = new EmployeePojo(0,"", "","testUserName","cat", userRolePojo);
		
		when(dao.findByEmpUserName(testInput.getEmpUserName())).thenReturn(testUser);
		when(mockService.checkPass("cat", testUser.getEmpHashedPassword())).thenReturn(true);
		EmployeePojo actualUser = service.findByEmpUserName(testInput);
		assertEquals(testUser, actualUser);
	}
	@Test
	void testEmpViewInfo() throws ApplicationException {
		when(dao.findById(1)).thenReturn(Optional.of(testUser));
		EmployeePojo actualUser = service.empViewInfo(1);
		assertEquals(testUser.getEmpUserName(), actualUser.getEmpUserName());
	}
	@Test
	void testEmpUpdateInfo() throws ApplicationException {
	
		when(dao.save(testUser)).thenReturn(testUser);
		EmployeePojo sendPojo = new EmployeePojo(1,"diffTestFirstName", "diffTestLastName","diffTestUserName"," $2a$10$HwO.e2gax/jJuW49MfLbvujyUQu8Wr6yppRHXFkLp11./Hnaj74Nu", userRolePojo);
		EmployeePojo actualUser = service.empUpdateInfo(sendPojo);
		assertEquals(sendPojo, actualUser);
	}
	@Test
	void testManViewAll() throws ApplicationException {
		
		
		EmployeeEntity diffTestUser1 = new EmployeeEntity(1,"diffFirstName1", "diffLastName1","diffUserName1","diffPassword1", userRole);
		EmployeeEntity diffTestUser2 = new EmployeeEntity(2,"diffFirstName2", "diffLastName2","diffUserName2","diffPassword2", userRole);
		List<EmployeeEntity> testAllEmployeesPojos = new ArrayList<EmployeeEntity>();
		
		testAllEmployeesPojos.add(diffTestUser1);
		testAllEmployeesPojos.add(diffTestUser2);
		when(dao.findAll()).thenReturn(testAllEmployeesPojos);
		List<EmployeePojo> actualUsers = service.manViewAll();
	
		
		assertEquals(2, testAllEmployeesPojos.size());
	}
}
