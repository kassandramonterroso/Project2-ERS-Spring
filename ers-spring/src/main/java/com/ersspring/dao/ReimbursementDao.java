package com.ersspring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.ersspring.entity.ReimbursementEntity;
import com.ersspring.entity.StatusEntity;
import com.ersspring.pojo.ReimbursementPojo;

@Repository
public interface ReimbursementDao extends JpaRepository<ReimbursementEntity, Integer> {

	@Query("FROM ReimbursementEntity WHERE reimb_status_id=1")
	List<ReimbursementEntity> findAllPending();

	@Query("FROM ReimbursementEntity WHERE reimb_status_id!=1")
	List<ReimbursementEntity> findAllResolved();

	@Query("UPDATE ReimbursementEntity SET reimb_status_id=: decision, approver_id=: approver WHERE reimb_id=: r")
	ReimbursementEntity updateRequest(@Param("decision") int decision, @Param("approver") int approver, @Param("r") int r);


}
