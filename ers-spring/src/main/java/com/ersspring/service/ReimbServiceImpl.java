package com.ersspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersspring.dao.ReimbursementDao;
import com.ersspring.entity.ReimbursementEntity;
import com.ersspring.exception.ApplicationException;
import com.ersspring.pojo.EmployeePojo;
import com.ersspring.pojo.ReimbursementPojo;
import com.ersspring.pojo.RolesPojo;
import com.ersspring.pojo.StatusPojo;

@Service
public class ReimbServiceImpl implements ReimbService {

	@Autowired
	ReimbursementDao reimbursementDao;
	
	public ReimbServiceImpl() {
	}

	@Override
	public List<ReimbursementPojo> viewAllRequests() throws ApplicationException {
		
		return null; 
	}

	@Override
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementPojo> viewAllRequestsByRequester(int requesterId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementPojo> viewPendingRequestsByRequester(int empid) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementPojo> viewResolvedRequestsByRequester(int empid) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException {
		List<ReimbursementEntity> allPendingRequestsEntity = reimbursementDao.findAllPending();
		List<ReimbursementPojo> allPendingRequestsPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedAllPendingRequestsEntity : allPendingRequestsEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedAllPendingRequestsEntity.getStatusEntity().getStatusId());
			statusPojo.setStatus(fetchedAllPendingRequestsEntity.getStatusEntity().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedAllPendingRequestsEntity.getRequester().getEmpId());
			employeePojoRequester.setEmpFirstName(fetchedAllPendingRequestsEntity.getRequester().getEmpFirstName());
			employeePojoRequester.setEmpLastName(fetchedAllPendingRequestsEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole.setRoleId(fetchedAllPendingRequestsEntity.getRequester().getRolesEntity().getRoleId());
			requesterRole.setRole(fetchedAllPendingRequestsEntity.getRequester().getRolesEntity().getRole());

			employeePojoRequester.setRolesPojo(requesterRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedAllPendingRequestsEntity.getReimbId(), fetchedAllPendingRequestsEntity.getReimbAmt(),
					statusPojo, employeePojoRequester);
			allPendingRequestsPojo.add(returnReimbursementPojo);
		}
		return allPendingRequestsPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException {
		List<ReimbursementEntity> allResolvedRequestsEntity = reimbursementDao.findAllResolved();
		List<ReimbursementPojo> allResolvedRequestsPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedAllResolvedRequestsEntity : allResolvedRequestsEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedAllResolvedRequestsEntity.getStatusEntity().getStatusId());
			statusPojo.setStatus(fetchedAllResolvedRequestsEntity.getStatusEntity().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedAllResolvedRequestsEntity.getRequester().getEmpId());
			employeePojoRequester.setEmpFirstName(fetchedAllResolvedRequestsEntity.getRequester().getEmpFirstName());
			employeePojoRequester.setEmpLastName(fetchedAllResolvedRequestsEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole.setRoleId(fetchedAllResolvedRequestsEntity.getRequester().getRolesEntity().getRoleId());
			requesterRole.setRole(fetchedAllResolvedRequestsEntity.getRequester().getRolesEntity().getRole());

			employeePojoRequester.setRolesPojo(requesterRole);

			EmployeePojo employeePojoApprover = new EmployeePojo();
			employeePojoApprover.setEmpId(fetchedAllResolvedRequestsEntity.getApprover().getEmpId());
			employeePojoApprover.setEmpFirstName(fetchedAllResolvedRequestsEntity.getApprover().getEmpFirstName());
			employeePojoApprover.setEmpLastName(fetchedAllResolvedRequestsEntity.getApprover().getEmpLastName());

			RolesPojo approverRole = new RolesPojo();
			approverRole.setRoleId(fetchedAllResolvedRequestsEntity.getApprover().getRolesEntity().getRoleId());
			approverRole.setRole(fetchedAllResolvedRequestsEntity.getApprover().getRolesEntity().getRole());

			employeePojoApprover.setRolesPojo(approverRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedAllResolvedRequestsEntity.getReimbId(), fetchedAllResolvedRequestsEntity.getReimbAmt(),
					statusPojo, employeePojoRequester, employeePojoApprover);
			allResolvedRequestsPojo.add(returnReimbursementPojo);
		}
		return allResolvedRequestsPojo;
	}

	@Override
	public ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		int decision = reimbursementPojo.getStatusPojo().getStatusId();
		int approver = reimbursementPojo.getApprover().getEmpId();
		int r = reimbursementPojo.getReimbId();
		ReimbursementEntity updatedRequest = reimbursementDao.updateRequest(decision, approver,r);
		
		
		return reimbursementPojo;
	}
}


























