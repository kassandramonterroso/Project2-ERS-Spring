package com.ersspring.service;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ersspring.dao.ImageReceiptDao;
import com.ersspring.entity.ImageReceiptEntity;
import com.ersspring.exceptions.ApplicationException;
import com.ersspring.pojo.ImageReceiptPojo;

@Service
public class ImageReceiptServiceImpl implements ImageReceiptService{
	
	@Autowired
	ImageReceiptDao imageReceiptDao;

	@Override
	public ImageReceiptPojo uploadImage(int reimbId, MultipartFile file) throws ApplicationException, IOException {
		
		ImageReceiptPojo imageReceiptPojo = new ImageReceiptPojo();
		imageReceiptPojo.setImage(file.getBytes());
		imageReceiptPojo.setImageReimbId(reimbId);
		
		ImageReceiptEntity imageReceiptEntity = new ImageReceiptEntity();
		
		BeanUtils.copyProperties(imageReceiptPojo, imageReceiptEntity);
		
		ImageReceiptEntity returnedImgEnt = imageReceiptDao.saveAndFlush(imageReceiptEntity);
		
		imageReceiptPojo.setImageId(returnedImgEnt.getImageId());
		return imageReceiptPojo;
	}

}
