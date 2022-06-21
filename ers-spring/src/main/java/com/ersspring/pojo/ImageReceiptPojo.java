package com.ersspring.pojo;

public class ImageReceiptPojo {

	private int imageId;
	private byte[] image;
	private int imageReimbId;

	public ImageReceiptPojo() {
	}

	
	
	public ImageReceiptPojo(byte[] image, int imageReimbId) {
		super();
		this.image = image;
		this.imageReimbId = imageReimbId;
	}



	public ImageReceiptPojo(int imageId, byte[] image, int imageReimbId) {
		super();
		this.imageId = imageId;
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
