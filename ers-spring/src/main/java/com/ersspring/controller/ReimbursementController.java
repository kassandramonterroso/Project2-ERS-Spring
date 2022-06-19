package com.ersspring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("reimbursement")
	public List<ReimbursementPojo> viewAllRequests() throws ApplicationException{
		return reimbService.viewAllRequests();
	}
	
	@PostMapping("reimbursement")
	public ReimbursementPojo submitRequest(@RequestBody ReimbursementPojo reimbursementPojo) throws ApplicationException {
		return reimbService.submitRequest(reimbursementPojo);
	}
	
	@GetMapping("reimbursement/{requesterId}")
	public List<ReimbursementPojo> viewAllRequestsByRequester(@PathVariable("requesterId") int requesterId) throws ApplicationException{
		return reimbService.viewAllRequestsByRequester(requesterId);
	}
	
	@GetMapping("pending/{empid}")
	public List<ReimbursementPojo> viewPendingRequestsByRequester(@PathVariable("empid") int empid) throws ApplicationException{
		return reimbService.viewPendingRequestsByRequester(empid);
	}
	
	@GetMapping("resolved/{empid}")
	public List<ReimbursementPojo> viewResolvedRequestsByRequester(@PathVariable("empid") int empid) throws ApplicationException{
		return reimbService.viewResolvedRequestsByRequester(empid);
	}
	
	@GetMapping("allPending")
	public List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException{
		return reimbService.viewAllPendingRequests();
	}
	
	@GetMapping("allResolved")
	public List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException{
		return reimbService.viewAllResolvedRequests();
	}
	
	@PutMapping("updateRequest")
	public ReimbursementPojo manUpdateRequest(@RequestBody ReimbursementPojo reimbursementPojo) throws ApplicationException {
		return reimbService.manUpdateRequest(reimbursementPojo);
	}
}
