package org.sid.model;

import org.springframework.web.multipart.MultipartFile;

public class FileData {
	
	MultipartFile pdf;
	MultipartFile image;
	int xAxis;
	int yAxis;
	
	
	public FileData(MultipartFile pdf, MultipartFile image, int xAxis, int yAxis) {
		super();
		this.pdf = pdf;
		this.image = image;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}
	
	public MultipartFile getPdf() {
		return pdf;
	}
	public void setPdf(MultipartFile pdf) {
		this.pdf = pdf;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public int getxAxis() {
		return xAxis;
	}
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	
	

}
