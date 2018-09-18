package com.ftn.SBZ_2018.netgear.service;

import java.util.List;

import com.ftn.SBZ_2018.netgear.model.Product;

public interface ProductService {

	public Product findById(Long id);
	public List<Product> getAllProducts();
	public List<Product> getAllProductsByType(String productType);
	public Product insertProduct(Product product);
	public Product updateProduct(Product product);
	public void deleteProduct(Long id);
}
