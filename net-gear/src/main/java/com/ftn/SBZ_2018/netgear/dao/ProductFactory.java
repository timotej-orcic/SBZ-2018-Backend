package com.ftn.SBZ_2018.netgear.dao;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.model.UploadedImage;

public class ProductFactory {

	public static Product createProduct(ProductDAO productDAO, MultipartFile imageFile, boolean isCreating) throws IOException {
		Product retObj = new Product();
		
		retObj.setType(productDAO.getType());
		retObj.setManufactorer(productDAO.getManufactorer());
		retObj.setDescription(productDAO.getDescription());
		retObj.setPrice(productDAO.getPrice());
		retObj.setWarrantyInMonths(productDAO.getWarrantyInMonths());
		
		if(isCreating) {
			UploadedImage image = new UploadedImage();
			image.setName(imageFile.getOriginalFilename());
			image.setType(imageFile.getContentType());
			image.setImageBytes(imageFile.getBytes());
			
			retObj.setBase64Image(image);
		}
		
		return retObj;
	}
	
	public static void setProduct(Product product, ProductDAO productDAO, MultipartFile imageFile) throws IOException {
		product.setType(productDAO.getType());
		product.setManufactorer(productDAO.getManufactorer());
		product.setDescription(productDAO.getDescription());
		product.setPrice(productDAO.getPrice());
		product.setWarrantyInMonths(productDAO.getWarrantyInMonths());
		
		if(!imageFile.getOriginalFilename().equals("")) {
			product.getBase64Image().setName(imageFile.getOriginalFilename());
			product.getBase64Image().setType(imageFile.getContentType());
			product.getBase64Image().setImageBytes(imageFile.getBytes());
		}
	}
}
