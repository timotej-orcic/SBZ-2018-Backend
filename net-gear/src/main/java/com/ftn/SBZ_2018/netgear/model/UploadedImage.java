package com.ftn.SBZ_2018.netgear.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class UploadedImage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=100)
	private String name;
	
	@Column(nullable=false, length=50)
	private String type;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(nullable=false)
	private byte[] imageBytes;
	
	public UploadedImage() {}

	public UploadedImage(Long id, String name, String type, byte[] imageBytes) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.imageBytes = imageBytes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}

	@Override
	public String toString() {
		return "UploadedImage [id=" + id + ", name=" + name + ", type=" + type + "]";
	}		
}
