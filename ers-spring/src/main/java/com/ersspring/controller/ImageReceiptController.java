package com.ersspring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ersspring.exceptions.ApplicationException;
import com.ersspring.pojo.ImageReceiptPojo;
import com.ersspring.service.ImageReceiptService;

@CrossOrigin
@RestController
@RequestMapping("api")
public class ImageReceiptController {
	
	@Autowired
	ImageReceiptService imageReceiptService;
	
	@PostMapping("image/{reimbid}")
	public ImageReceiptPojo uploadImage(@PathVariable("reimbid") int reimbId, @RequestParam ("myFile") MultipartFile file) throws ApplicationException, IOException {
	return imageReceiptService.uploadImage(reimbId, file);
	}
}


