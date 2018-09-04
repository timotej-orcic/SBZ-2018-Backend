package com.ftn.SBZ_2018.netgear.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ftn.SBZ_2018.netgear.dao.ResponseWrapper;
import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.service.ProductService;

@RestController
@RequestMapping(value = "rest/secured/admin/")
public class AdminController {

	@Autowired
	private ProductService productService;
	
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
	@RequestMapping(value = "addProduct", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Product> addProduct(@RequestParam("productForm") MultipartFile[] productForm) {
		ResponseWrapper<Product> retObj = new ResponseWrapper<Product>();
		
		
		
		return retObj;
	}
}
