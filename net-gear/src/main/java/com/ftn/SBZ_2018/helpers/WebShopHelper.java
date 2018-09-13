package com.ftn.SBZ_2018.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ftn.SBZ_2018.netgear.dao.SearchProduct;
import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.model.ShoppingCart;
import com.ftn.SBZ_2018.netgear.service.ProductService;
import com.ftn.SBZ_2018.netgear.service.ShoppingCartService;

public class WebShopHelper {

	public static List<String> getProductTypes(ProductService productService) {
		List<String> retList = new ArrayList<String>();
		
		productService.getAllProducts().forEach(product -> {
			if(!retList.contains(product.getType())) {
				retList.add(product.getType());
			}
		});
		
		return retList;
	}
	
	public static List<String> getManufactorers(ProductService productService) {
		List<String> retList = new ArrayList<String>();
		
		productService.getAllProducts().forEach(product -> {
			if(!retList.contains(product.getManufactorer())) {
				retList.add(product.getManufactorer());
			}
		});
		
		return retList;
	}
	
	public static List<Product> searchProducts(ProductService productService, SearchProduct searchProduct) {
		
		List<Product> result = productService.getAllProducts().stream()
				.filter(p -> p.getType().equals(searchProduct.getProductType()))
				.collect(Collectors.toList());
		
		if(searchProduct.getManufacorer() != null) {
			result = result.stream()
					.filter(p -> p.getManufactorer().equals(searchProduct.getManufacorer()))
					.collect(Collectors.toList());
		}
		
		if(searchProduct.getPriceMin() != null) {
			result = result.stream()
					.filter(p -> p.getPrice() >= searchProduct.getPriceMin())
					.collect(Collectors.toList());
		}
		
		if(searchProduct.getPriceMax() != null) {
			result = result.stream()
					.filter(p -> p.getPrice() <= searchProduct.getPriceMax())
					.collect(Collectors.toList());
		}
		
		result = result.stream()
				.filter(p -> p.getWarrantyInMonths() >= searchProduct.getWarrantyInMonthsMin())
				.collect(Collectors.toList());
		
		return result;
	}
	
	public static List<Product> getAllUserShoppedProductsByType(ShoppingCartService shoppingCartService, Long userId, String productType) {
		List<ShoppingCart> myCarts = shoppingCartService.getAllUserCarts(userId);
		List<Product> result = new ArrayList<Product>();
		
		myCarts.forEach(cart -> {
			Set<Product> products = cart.getProductsCart();
			products.forEach(product -> {
				if(product.getType().equals(productType)) {
					result.add(product);
				}
			});
		});
		
		return result;
	}
}
