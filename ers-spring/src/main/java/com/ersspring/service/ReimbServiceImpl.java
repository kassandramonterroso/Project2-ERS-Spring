package com.ersspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersspring.dao.ReimbursementDao;
import com.ersspring.entity.EmployeeEntity;
import com.ersspring.entity.ReimbursementEntity;
import com.ersspring.entity.StatusEntity;
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
		
		List<ReimbursementEntity> allRequestsEnt = reimbursementDao.findAll();
		
		List<ReimbursementPojo> allRequestsPojo = new ArrayList<ReimbursementPojo>();
		
		for (ReimbursementEntity fetchedReimbEnt : allRequestsEnt) {
			StatusPojo status = new StatusPojo();
			status.setStatusId(fetchedReimbEnt.getStatus().getStatusId());
			status.setStatus(fetchedReimbEnt.getStatus().getStatus());
			EmployeePojo requester = new EmployeePojo();
			requester.setEmpId(fetchedReimbEnt.getRequester().getEmpId());
			requester.setEmpFirstName(fetchedReimbEnt.getRequester().getEmpFirstName());
			requester.setEmpLastName(fetchedReimbEnt.getRequester().getEmpLastName());
			EmployeePojo approver = new EmployeePojo();
			if (fetchedReimbEnt.getApprover() != null) {
				approver.setEmpId(fetchedReimbEnt.getApprover().getEmpId());
				approver.setEmpFirstName(fetchedReimbEnt.getApprover().getEmpFirstName());
				approver.setEmpLastName(fetchedReimbEnt.getApprover().getEmpLastName());
			}
			ReimbursementPojo returnReimbPojo = new ReimbursementPojo(fetchedReimbEnt.getReimbId(),
					fetchedReimbEnt.getReimbAmt(), status, requester, approver);
			allRequestsPojo.add(returnReimbPojo);

		}
		return allRequestsPojo;
	}

	@Override
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		
		StatusEntity statusEnt = new StatusEntity();
		statusEnt.setStatusId(reimbursementPojo.getStatus().getStatusId());

		EmployeeEntity requesterEnt = new EmployeeEntity();
		requesterEnt.setEmpId(reimbursementPojo.getRequester().getEmpId());

		ReimbursementEntity submitReimbEntity = new ReimbursementEntity(reimbursementPojo.getReimbAmt(), statusEnt,
				requesterEnt);
		
		ReimbursementEntity returnedReimbEnt = reimbursementDao.saveAndFlush(submitReimbEntity);
		
		reimbursementPojo.setReimbId(returnedReimbEnt.getReimbId());
		
		return reimbursementPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllRequestsByRequester(int requesterId) throws ApplicationException {
		List<ReimbursementEntity> allReimbEnt = reimbursementDao.findByRequester_EmpId(requesterId);
		
		List<ReimbursementPojo> allReimbPojo = new ArrayList<ReimbursementPojo>();
		
		for (ReimbursementEntity fetchedRequestsByRequesterEntity : allReimbEnt) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedRequestsByRequesterEntity.getStatus().getStatusId());
			statusPojo.setStatus(fetchedRequestsByRequesterEntity.getStatus().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedRequestsByRequesterEntity.getRequester().getEmpId());
			employeePojoRequester
					.setEmpFirstName(fetchedRequestsByRequesterEntity.getRequester().getEmpFirstName());
			employeePojoRequester.setEmpLastName(fetchedRequestsByRequesterEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole.setRoleId(fetchedRequestsByRequesterEntity.getRequester().getRoles().getRoleId());
			requesterRole.setRole(fetchedRequestsByRequesterEntity.getRequester().getRoles().getRole());

			employeePojoRequester.setRoles(requesterRole);

			EmployeePojo employeePojoApprover = new EmployeePojo();
			if (fetchedRequestsByRequesterEntity.getApprover() != null) {
				employeePojoApprover.setEmpId(fetchedRequestsByRequesterEntity.getApprover().getEmpId());
				employeePojoApprover
						.setEmpFirstName(fetchedRequestsByRequesterEntity.getApprover().getEmpFirstName());
				employeePojoApprover
						.setEmpLastName(fetchedRequestsByRequesterEntity.getApprover().getEmpLastName());

				RolesPojo approverRole = new RolesPojo();
				approverRole.setRoleId(fetchedRequestsByRequesterEntity.getApprover().getRoles().getRoleId());
				approverRole.setRole(fetchedRequestsByRequesterEntity.getApprover().getRoles().getRole());

				employeePojoApprover.setRoles(approverRole);
			}

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedRequestsByRequesterEntity.getReimbId(), fetchedRequestsByRequesterEntity.getReimbAmt(),
					statusPojo, employeePojoRequester, employeePojoApprover);
			allReimbPojo.add(returnReimbursementPojo);
		}
		
		return allReimbPojo;
	}

	@Override
	public List<ReimbursementPojo> viewPendingRequestsByRequester(int empid) throws ApplicationException {
		
		List<ReimbursementEntity> allPendingRequestsByRequesterEntity = reimbursementDao.findByRequester_EmpIdAndStatus_StatusId(empid, 1);
		List<ReimbursementPojo> allPendingRequestsByRequesterPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedPendingRequestsByRequesterEntity : allPendingRequestsByRequesterEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedPendingRequestsByRequesterEntity.getStatus().getStatusId());
			statusPojo.setStatus(fetchedPendingRequestsByRequesterEntity.getStatus().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedPendingRequestsByRequesterEntity.getRequester().getEmpId());
			employeePojoRequester
					.setEmpFirstName(fetchedPendingRequestsByRequesterEntity.getRequester().getEmpFirstName());
			employeePojoRequester
					.setEmpLastName(fetchedPendingRequestsByRequesterEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole
					.setRoleId(fetchedPendingRequestsByRequesterEntity.getRequester().getRoles().getRoleId());
			requesterRole.setRole(fetchedPendingRequestsByRequesterEntity.getRequester().getRoles().getRole());

			employeePojoRequester.setRoles(requesterRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedPendingRequestsByRequesterEntity.getReimbId(),
					fetchedPendingRequestsByRequesterEntity.getReimbAmt(), statusPojo, employeePojoRequester);
			allPendingRequestsByRequesterPojo.add(returnReimbursementPojo);
		}
		return allPendingRequestsByRequesterPojo;
	}

	@Override
	public List<ReimbursementPojo> viewResolvedRequestsByRequester(int empid) throws ApplicationException {
		List<ReimbursementEntity> allResolvedRequestsByRequesterEntity = reimbursementDao.findByRequester_EmpIdAndStatus_StatusIdIsNot(empid, 1);
		List<ReimbursementPojo> allResolvedRequestsByRequesterPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedResolvedRequestsByRequesterEntity : allResolvedRequestsByRequesterEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedResolvedRequestsByRequesterEntity.getStatus().getStatusId());
			statusPojo.setStatus(fetchedResolvedRequestsByRequesterEntity.getStatus().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedResolvedRequestsByRequesterEntity.getRequester().getEmpId());
			employeePojoRequester
					.setEmpFirstName(fetchedResolvedRequestsByRequesterEntity.getRequester().getEmpFirstName());
			employeePojoRequester
					.setEmpLastName(fetchedResolvedRequestsByRequesterEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole.setRoleId(
					fetchedResolvedRequestsByRequesterEntity.getRequester().getRoles().getRoleId());
			requesterRole
					.setRole(fetchedResolvedRequestsByRequesterEntity.getRequester().getRoles().getRole());

			employeePojoRequester.setRoles(requesterRole);

			EmployeePojo employeePojoApprover = new EmployeePojo();
			employeePojoApprover.setEmpId(fetchedResolvedRequestsByRequesterEntity.getApprover().getEmpId());
			employeePojoApprover
					.setEmpFirstName(fetchedResolvedRequestsByRequesterEntity.getApprover().getEmpFirstName());
			employeePojoApprover
					.setEmpLastName(fetchedResolvedRequestsByRequesterEntity.getApprover().getEmpLastName());

			RolesPojo approverRole = new RolesPojo();
			approverRole
					.setRoleId(fetchedResolvedRequestsByRequesterEntity.getApprover().getRoles().getRoleId());
			approverRole.setRole(fetchedResolvedRequestsByRequesterEntity.getApprover().getRoles().getRole());

			employeePojoApprover.setRoles(approverRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedResolvedRequestsByRequesterEntity.getReimbId(),
					fetchedResolvedRequestsByRequesterEntity.getReimbAmt(), statusPojo, employeePojoRequester,
					employeePojoApprover);
			allResolvedRequestsByRequesterPojo.add(returnReimbursementPojo);
		}
		return allResolvedRequestsByRequesterPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException {
		List<ReimbursementEntity> allPendingRequestsEntity = reimbursementDao.findByStatus_StatusId(1);

		List<ReimbursementPojo> allPendingRequestsPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedAllPendingRequestsEntity : allPendingRequestsEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedAllPendingRequestsEntity.getStatus().getStatusId());
			statusPojo.setStatus(fetchedAllPendingRequestsEntity.getStatus().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedAllPendingRequestsEntity.getRequester().getEmpId());
			employeePojoRequester.setEmpFirstName(fetchedAllPendingRequestsEntity.getRequester().getEmpFirstName());
			employeePojoRequester.setEmpLastName(fetchedAllPendingRequestsEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole.setRoleId(fetchedAllPendingRequestsEntity.getRequester().getRoles().getRoleId());
			requesterRole.setRole(fetchedAllPendingRequestsEntity.getRequester().getRoles().getRole());

			employeePojoRequester.setRoles(requesterRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedAllPendingRequestsEntity.getReimbId(), fetchedAllPendingRequestsEntity.getReimbAmt(),
					statusPojo, employeePojoRequester);
			allPendingRequestsPojo.add(returnReimbursementPojo);
		}
		return allPendingRequestsPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException {
		List<ReimbursementEntity> allResolvedRequestsEntity = reimbursementDao.findByStatus_StatusIdIsNot(1);

		List<ReimbursementPojo> allResolvedRequestsPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedAllResolvedRequestsEntity : allResolvedRequestsEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedAllResolvedRequestsEntity.getStatus().getStatusId());
			statusPojo.setStatus(fetchedAllResolvedRequestsEntity.getStatus().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedAllResolvedRequestsEntity.getRequester().getEmpId());
			employeePojoRequester.setEmpFirstName(fetchedAllResolvedRequestsEntity.getRequester().getEmpFirstName());
			employeePojoRequester.setEmpLastName(fetchedAllResolvedRequestsEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole.setRoleId(fetchedAllResolvedRequestsEntity.getRequester().getRoles().getRoleId());
			requesterRole.setRole(fetchedAllResolvedRequestsEntity.getRequester().getRoles().getRole());

			employeePojoRequester.setRoles(requesterRole);

			EmployeePojo employeePojoApprover = new EmployeePojo();
			employeePojoApprover.setEmpId(fetchedAllResolvedRequestsEntity.getApprover().getEmpId());
			employeePojoApprover.setEmpFirstName(fetchedAllResolvedRequestsEntity.getApprover().getEmpFirstName());
			employeePojoApprover.setEmpLastName(fetchedAllResolvedRequestsEntity.getApprover().getEmpLastName());

			RolesPojo approverRole = new RolesPojo();
			approverRole.setRoleId(fetchedAllResolvedRequestsEntity.getApprover().getRoles().getRoleId());
			approverRole.setRole(fetchedAllResolvedRequestsEntity.getApprover().getRoles().getRole());

			employeePojoApprover.setRoles(approverRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedAllResolvedRequestsEntity.getReimbId(), fetchedAllResolvedRequestsEntity.getReimbAmt(),
					statusPojo, employeePojoRequester, employeePojoApprover);
			allResolvedRequestsPojo.add(returnReimbursementPojo);
		}
		return allResolvedRequestsPojo;
	}

	@Override
	public ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
	
		Optional<ReimbursementEntity> reimbEntityOpt = reimbursementDao.findById(reimbursementPojo.getReimbId());
		
		ReimbursementEntity fetchedReimbEntity = reimbEntityOpt.get();
		
		StatusEntity statusEnt = new StatusEntity();
		statusEnt.setStatusId(reimbursementPojo.getStatus().getStatusId());
		fetchedReimbEntity.setStatus(statusEnt);
		
		EmployeeEntity approverEnt = new EmployeeEntity();
		approverEnt.setEmpId(reimbursementPojo.getApprover().getEmpId());
		fetchedReimbEntity.setApprover(approverEnt);
		
		reimbursementDao.save(fetchedReimbEntity);
				
		return reimbursementPojo;
	}	
}