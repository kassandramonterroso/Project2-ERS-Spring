package com.ersspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersspring.dao.ReimbursementDao;
import com.ersspring.exception.ApplicationException;
import com.ersspring.pojo.ReimbursementPojo;

@Service
public class ReimbServiceImpl implements ReimbService {

	@Autowired
	ReimbursementDao reimbursementDao;
	
	public ReimbServiceImpl() {
	}

	@Override
	public List<ReimbursementPojo> viewAllRequests() throws ApplicationException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
}
