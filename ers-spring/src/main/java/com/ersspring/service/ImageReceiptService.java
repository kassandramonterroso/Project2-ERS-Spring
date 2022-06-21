package com.ersspring.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ersspring.exception.ApplicationException;
import com.ersspring.pojo.ImageReceiptPojo;


public interface ImageReceiptService {

	public ImageReceiptPojo uploadImage (int reimbId, MultipartFile file) throws ApplicationException, IOException;
	
}
