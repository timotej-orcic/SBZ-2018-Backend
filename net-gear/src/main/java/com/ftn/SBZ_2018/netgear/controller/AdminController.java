package com.ftn.SBZ_2018.netgear.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ftn.SBZ_2018.netgear.dao.DiscountDAO;
import com.ftn.SBZ_2018.netgear.dao.DiscountFactory;
import com.ftn.SBZ_2018.netgear.dao.ProductDAO;
import com.ftn.SBZ_2018.netgear.dao.ProductFactory;
import com.ftn.SBZ_2018.netgear.dao.ResponseWrapper;
import com.ftn.SBZ_2018.netgear.model.Discount;
import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.service.DiscountService;
import com.ftn.SBZ_2018.netgear.service.ProductService;

@RestController
@RequestMapping(value = "rest/secured/admin/")
public class AdminController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private DiscountService discountService;
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value = "getProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<List<Product>> getProducts() {
		ResponseWrapper<List<Product>> retObj = new ResponseWrapper<List<Product>>();
		
		retObj.setPayload(productService.getAllProducts());
		retObj.setMessage("");
		retObj.setSuccess(true);
		
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value = "getDiscounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<List<Discount>> getDiscounts() {
		ResponseWrapper<List<Discount>> retObj = new ResponseWrapper<List<Discount>>();
		
		retObj.setPayload(discountService.getAllDiscounts());
		retObj.setMessage("");
		retObj.setSuccess(true);
		
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value = "addProduct", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Product> addProduct(@RequestPart("product") ProductDAO productDAO, @RequestPart("image") MultipartFile image) {
		ResponseWrapper<Product> retObj = new ResponseWrapper<Product>();
		
		Product p = null;
		try {
			p = ProductFactory.createProduct(productDAO, image, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(p == null) {
			retObj.setPayload(null);
			retObj.setMessage("Image upload failure, please try again");
			retObj.setSuccess(false);
		}
		else {
			productService.insertProduct(p);
			retObj.setPayload(p);
			retObj.setMessage("New product added successfully");
			retObj.setSuccess(true);
		}
		
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value = "editProduct", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Product> editProduct(@RequestPart("product") ProductDAO productDAO, @RequestPart("image") MultipartFile image) {
		ResponseWrapper<Product> retObj = new ResponseWrapper<Product>();
		
		Product p = productService.findById(productDAO.getId());
		
		if(p == null) {
			retObj.setPayload(null);
			retObj.setMessage("Product read failure, please try again");
			retObj.setSuccess(false);
		}
		else {
			boolean succesfull = true;
			try {
				ProductFactory.setProduct(p, productDAO, image);
			} catch (IOException e) {
				e.printStackTrace();
				succesfull = false;
			}
			
			if(succesfull) {
				productService.updateProduct(p);
				retObj.setPayload(p);
				retObj.setMessage("Product updated successfully");
				retObj.setSuccess(true);
			}
			else {
				retObj.setPayload(null);
				retObj.setMessage("Image upload failure, please try again");
				retObj.setSuccess(false);
			}
		}
		
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value = "deleteProduct", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Long> deleteProduct(@RequestParam(value = "id", required = true) Long id) {
		ResponseWrapper<Long> retObj = new ResponseWrapper<Long>();
		
		Product p = productService.findById(id);
		if(p == null) {
			retObj.setPayload(null);
			retObj.setMessage("Product read failure, please try again");
			retObj.setSuccess(false);
		}
		else {
			productService.deleteProduct(id);
			retObj.setPayload(id);
			retObj.setMessage("Product deleted successfully");
			retObj.setSuccess(true);
		}
		
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value = "addDiscount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Discount> addDiscount(@RequestBody DiscountDAO discount) {
		ResponseWrapper<Discount> retObj = new ResponseWrapper<Discount>();
		
		Discount disc = DiscountFactory.createDiscount(discount);
		discountService.insertDiscount(disc);
		retObj.setPayload(disc);
		retObj.setMessage("Discount added successfully");
		retObj.setSuccess(true);
		
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value = "editDiscount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Discount> editDiscount(@RequestBody DiscountDAO discount) {
		ResponseWrapper<Discount> retObj = new ResponseWrapper<Discount>();
		
		Discount disc = discountService.findById(discount.getId());
		if(disc == null) {
			retObj.setPayload(null);
			retObj.setMessage("Discount read failure, please try again");
			retObj.setSuccess(false);
		}
		else {
			DiscountFactory.setDiscount(disc, discount);
			discountService.updateDiscount(disc);
			retObj.setPayload(disc);
			retObj.setMessage("Discount updated successfully");
			retObj.setSuccess(true);
		}				
		
		return retObj;
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value = "deleteDiscount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Long> deleteDiscount(@RequestParam(value = "id", required = true) Long id) {
		ResponseWrapper<Long> retObj = new ResponseWrapper<Long>();
		
		Discount d = discountService.findById(id);
		if(d == null) {
			retObj.setPayload(null);
			retObj.setMessage("Discount read failure, please try again");
			retObj.setSuccess(false);
		}
		else {
			discountService.deleteDiscount(id);
			retObj.setPayload(id);
			retObj.setMessage("Discount deleted successfully");
			retObj.setSuccess(true);
		}
		
		return retObj;
	}
}
