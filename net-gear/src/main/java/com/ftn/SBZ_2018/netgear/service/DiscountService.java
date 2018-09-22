package com.ftn.SBZ_2018.netgear.service;

import java.util.List;

import com.ftn.SBZ_2018.netgear.model.Discount;

public interface DiscountService {

	public Discount findById(Long id);
	public List<Discount> getAllDiscounts();
	public Discount insertDiscount(Discount discount);
	public Discount updateDiscount(Discount discount);
	public void deleteDiscount(Long id);
}
