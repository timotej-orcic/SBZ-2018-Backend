package com.ftn.SBZ_2018.netgear.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Preference {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JsonBackReference(value="userPreference")
	private User user;
	
	@Column(nullable=false, length=50)
	private String productType;
	
	@ManyToOne
	@JsonBackReference(value="preferenceType")
	private PreferenceType preferenceType;
	
	@Column(nullable=false, length=50)
	private String value;
	
	@Min(0)
	@Max(1)
	@Column(nullable=false)
	private Double percentage;
	
	public Preference() {}

	public Preference(Long id, User user, String productType, PreferenceType preferenceType, @Min(0) @Max(1) Double percentage) {
		this.id = id;
		this.user = user;
		this.productType = productType;
		this.preferenceType = preferenceType;
		this.percentage = percentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public PreferenceType getPreferenceType() {
		return preferenceType;
	}

	public void setPreferenceType(PreferenceType preferenceType) {
		this.preferenceType = preferenceType;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Preference [id=" + id + ", user=" + user + ", productType=" + productType + ", preferenceType="
				+ preferenceType + ", percentage=" + percentage + "]";
	}
}
