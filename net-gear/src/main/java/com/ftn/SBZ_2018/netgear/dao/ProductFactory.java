package com.ftn.SBZ_2018.netgear.dao;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ftn.SBZ_2018.netgear.model.Product;
import com.ftn.SBZ_2018.netgear.model.UploadedImage;

public class ProductFactory {

	public static Product getProduct(ProductDAO productDAO, MultipartFile imageFile) throws IOException {
		Product retObj = new Product();
		
		retObj.setName(productDAO.getName());
		retObj.setManufactorer(productDAO.getManufactorer());
		retObj.setDescription(productDAO.getDescription());
		retObj.setPrice(productDAO.getPrice());
		retObj.setWarrantyInMonths(productDAO.getWarrantyInMonths());
		
		UploadedImage image = new UploadedImage();
		image.setName(imageFile.getOriginalFilename());
		image.setType(imageFile.getContentType());
		image.setImageBytes(imageFile.getBytes());
		
		retObj.setBase64Image(image);
		
		return retObj;
	}
}
