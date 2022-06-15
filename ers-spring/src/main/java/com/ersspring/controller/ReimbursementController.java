package com.ersspring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ersspring.exception.ApplicationException;
import com.ersspring.pojo.ReimbursementPojo;
import com.ersspring.service.ReimbService;

@CrossOrigin // to enable cors
@RestController
@RequestMapping("api")
public class ReimbursementController {
	
	@Autowired
	ReimbService reimbService;

	// create the rest methods for the rest enpoints
	// http://localhost:5555/api/reimb
	
	@GetMapping("reimb")
	public List<ReimbursementPojo> viewAllRequests() throws ApplicationException {
		return reimbService.viewAllRequests();
	}
	
	@PostMapping("reimb")
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		return reimbService.submitRequest(reimbursementPojo);
	}
	@GetMapping("reimb/{requesterId}")
	public List<ReimbursementPojo> viewAllRequestsByRequester(int requesterId) throws ApplicationException {
		return reimbService.viewAllRequestsByRequester(requesterId);
	}
	
	@GetMapping("allPending")
	public List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException {
		return reimbService.viewAllPendingRequests();
	}
	
	@GetMapping("allResolved")
	public List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException {
		return reimbService.viewAllResolvedRequests();
	}
	
	@PutMapping("updateRequest")
	public ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		return reimbService.manUpdateRequest(reimbursementPojo);
	}
}
