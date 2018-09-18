package com.ftn.SBZ_2018.netgear.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ftn.SBZ_2018.netgear.dao.SearchData;
import com.ftn.SBZ_2018.netgear.dao.SearchProduct;
import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.model.ShoppingCart;
import com.ftn.SBZ_2018.netgear.model.ShoppingCartItem;
import com.ftn.SBZ_2018.netgear.service.ProductService;
import com.ftn.SBZ_2018.netgear.service.ShoppingCartService;

public class WebShopHelper {

	/*public static List<String> getProductTypes(ProductService productService) {
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
	}*/
	
	public static List<SearchData> getSearchData(ProductService productService) {
		List<SearchData> result = new ArrayList<SearchData>();
		
		List<String> productTypes = new ArrayList<String>();
		productService.getAllProducts().forEach(product -> {
			if(!productTypes.contains(product.getType())) {
				productTypes.add(product.getType());
			}
		});
		productTypes.forEach(pt -> {
			List<String> manufactorers = new ArrayList<String>();
			productService.getAllProductsByType(pt).forEach(product -> {
				if(!manufactorers.contains(product.getManufactorer())) {
					manufactorers.add(product.getManufactorer());
				}
			});
			SearchData searchData = new SearchData();
			searchData.setProductType(pt);
			searchData.setManufactorers(manufactorers);
			result.add(searchData);
		});
		
		return result;
	}
	
	public static List<Product> searchProducts(ProductService productService, SearchProduct searchProduct) {
		
		List<Product> result = productService.getAllProducts().stream()
				.filter(p -> p.getType().equals(searchProduct.getProductType()))
				.collect(Collectors.toList());
		
		if(searchProduct.getManufactorer() != "") {
			result = result.stream()
					.filter(p -> p.getManufactorer().equals(searchProduct.getManufactorer()))
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
	
	public static List<ShoppingCartItem> getAllUserShoppedItemsByType(ShoppingCartService shoppingCartService, Long userId, String productType) {
		List<ShoppingCart> myCarts = shoppingCartService.getAllUserCarts(userId);
		List<ShoppingCartItem> result = new ArrayList<ShoppingCartItem>();
		
		myCarts.forEach(cart -> {
			Set<ShoppingCartItem> items = cart.getItems();
			items.forEach(item -> {
				if(item.getProduct().getType().equals(productType)) {
					result.add(item);
				}
			});
		});
		
		return result;
	}
}
