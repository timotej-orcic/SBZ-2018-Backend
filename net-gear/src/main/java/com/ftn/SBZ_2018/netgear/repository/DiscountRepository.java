package com.ftn.SBZ_2018.netgear.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018.netgear.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

	public Discount findOneById(Long id);
}
