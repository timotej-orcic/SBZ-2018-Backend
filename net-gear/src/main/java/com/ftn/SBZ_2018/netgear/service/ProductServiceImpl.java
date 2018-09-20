package com.ftn.SBZ_2018.netgear.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Product findById(Long id) {
		return productRepo.findOneById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> getAllProductsByType(String productType) {
		return productRepo.findAll().stream()
				.filter(p -> p.getType().equals(productType))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getAllProductsByTypeAndSpecialLabel(String productType, int min, int max) {
		return productRepo.findAll().stream()
				.filter(p -> p.getType().equals(productType) && min <= p.getSpecialLabel() && p.getSpecialLabel() <= max)
				.collect(Collectors.toList());
	}
	
	@Override
	public Product insertProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
}
