package com.ersspring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ersspring.entity.ReimbursementEntity;
import com.ersspring.exceptions.ApplicationException;

@Repository
public interface ReimbursementDao extends JpaRepository<ReimbursementEntity, Integer> {

	List<ReimbursementEntity> findByRequester_EmpId(int requesterId) throws ApplicationException;

	List<ReimbursementEntity> findByRequester_EmpIdAndStatus_StatusId(int empid, int statusId) throws ApplicationException;

	List<ReimbursementEntity> findByRequester_EmpIdAndStatus_StatusIdIsNot(int empid, int statusId) throws ApplicationException;
	
	List<ReimbursementEntity> findByStatus_StatusId(int statusId) throws ApplicationException;

	List<ReimbursementEntity> findByStatus_StatusIdIsNot(int statusId) throws ApplicationException;


}
