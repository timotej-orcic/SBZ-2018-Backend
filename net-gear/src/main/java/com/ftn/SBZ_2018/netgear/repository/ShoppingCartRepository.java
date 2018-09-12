package com.ftn.SBZ_2018.netgear.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018.netgear.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	public ShoppingCart findOneById(Long id);
}
