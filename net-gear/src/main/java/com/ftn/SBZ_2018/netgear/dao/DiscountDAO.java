package com.ftn.SBZ_2018.netgear.dao;

import java.util.Date;

public class DiscountDAO {

	private Long id;
	private Long typeId;
	private Date beginDate;
	private Date endDate;
	private Long discObjId;
	private int value;
	
	public DiscountDAO () {}

	public DiscountDAO(Long id, Long typeId, Date beginDate, Date endDate, Long discObjId, int value) {
		this.id = id;
		this.typeId = typeId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.discObjId = discObjId;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DiscountDAO [id=" + id + ", typeId=" + typeId + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", discObjId=" + discObjId + ", value=" + value + "]";
	}
}
