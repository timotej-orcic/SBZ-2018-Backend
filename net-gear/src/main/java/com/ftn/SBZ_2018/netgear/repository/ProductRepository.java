package com.ftn.SBZ_2018.netgear.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018.netgear.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findOneById(Long id);
}
