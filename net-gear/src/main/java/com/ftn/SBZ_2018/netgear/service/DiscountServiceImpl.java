package com.ftn.SBZ_2018.netgear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018.netgear.model.Discount;
import com.ftn.SBZ_2018.netgear.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountRepository discountRepo;
	
	@Override
	public Discount findById(Long id) {
		return discountRepo.findOneById(id);
	}

	@Override
	public List<Discount> getAllDiscounts() {
		return discountRepo.findAll();
	}

	@Override
	public Discount insertDiscount(Discount discount) {
		return discountRepo.save(discount);
	}

	@Override
	public Discount updateDiscount(Discount discount) {
		return discountRepo.save(discount);
	}

	@Override
	public void deleteDiscount(Long id) {
		discountRepo.deleteById(id);
	}
}
