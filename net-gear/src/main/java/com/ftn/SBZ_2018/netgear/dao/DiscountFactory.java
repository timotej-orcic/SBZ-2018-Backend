package com.ftn.SBZ_2018.netgear.dao;

import com.ftn.SBZ_2018.netgear.model.Discount;

public class DiscountFactory {

	public static Discount createDiscount(DiscountDAO dao) {
		Discount retObj = new Discount();
		
		retObj.setDiscountType(new Long(0));
		retObj.setBeginDate(dao.getBeginDate());
		retObj.setEndDate(dao.getEndDate());
		retObj.setDiscObjId(dao.getDiscObjId());
		retObj.setDiscValue(dao.getValue());
		
		return retObj;
	}
	
	public static void setDiscount(Discount d, DiscountDAO dao) {
		d.setEndDate(dao.getEndDate());
		d.setDiscValue(dao.getValue());
	}
}
