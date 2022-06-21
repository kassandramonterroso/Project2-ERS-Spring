package com.ersspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image_receipt")
public class ImageReceiptEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private int imageId;

	@Column(name = "image")
	private byte[] image;
	
	@Column(name="image_reimb_id")
	private int imageReimbId;

	public ImageReceiptEntity() {
	}

	public ImageReceiptEntity(int imageId, byte[] image, int imageReimbId) {
		super();
		this.imageId = imageId;
		this.image = image;
		this.imageReimbId = imageReimbId;
	}

	public ImageReceiptEntity(byte[] image, int imageReimbId) {
		this.image = image;
		this.imageReimbId = imageReimbId;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getImageReimbId() {
		return imageReimbId;
	}

	public void setImageReimbId(int imageReimbId) {
		this.imageReimbId = imageReimbId;
	}

	@Override
	public String toString() {
		return "ImageReceiptEntity [imageId=" + imageId + ", image=" + image + ", imageReimbId=" + imageReimbId + "]";
	}
	
}
