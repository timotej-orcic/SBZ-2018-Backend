package com.ftn.SBZ_2018.netgear.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

@Entity
@Table(
	    uniqueConstraints = @UniqueConstraint(columnNames={"discountTypeId", "discObjId"})
)
public class Discount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, name="discountTypeId")
	private Long discountTypeId;
	
	@Column(nullable=false)
	private Date beginDate;
	
	@Column(nullable=false)
	private Date endDate;
	
	@Column(nullable=false,name="discObjId")
	private Long discObjId;
	
	@Column(nullable=false)
	@Min(0)
	private int discValue;

	public Discount() {}
	
	public Discount(Long id, Long discountTypeId, Date beginDate,
			Date endDate, Long discObjId, int discValue) {
		this.id = id;
		this.discountTypeId = discountTypeId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.discObjId = discObjId;
		this.discValue = discValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDiscountTypeId() {
		return discountTypeId;
	}

	public void setDiscountType(Long discountTypeId) {
		this.discountTypeId = discountTypeId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getDiscObjId() {
		return discObjId;
	}

	public void setDiscObjId(Long discObjId) {
		this.discObjId = discObjId;
	}

	public int getDiscValue() {
		return discValue;
	}

	public void setDiscValue(int discValue) {
		this.discValue = discValue;
	}

	@Override
	public String toString() {
		return "Discount [id=" + id + ", discountTypeId=" + discountTypeId + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", discObjId=" + discObjId + ", discValue=" + discValue + "]";
	}
}
