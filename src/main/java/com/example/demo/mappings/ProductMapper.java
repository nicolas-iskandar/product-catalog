package com.example.demo.mappings;

import org.springframework.stereotype.Component;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.models.Product;

@Component
public class ProductMapper {
	public Product productDTOToProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setCategory(productDTO.getCategory());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setImagePath(productDTO.getImagePath());
		return product;
	}
}
