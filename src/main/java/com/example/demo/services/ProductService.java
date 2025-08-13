package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mappings.ProductMapper;
import com.example.demo.models.Product;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService {

	private final ProductRepo repo;
	private final ProductMapper mapper;

	public ProductService(ProductRepo repo, ProductMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public List<Product> getProducts() {
		return repo.findAll();
	}

	public Product getProductById(int id) {
		return repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
	}

	public Product createProduct(ProductDTO productDTO) {
		return repo.save(mapper.productDTOToProduct(productDTO));
	}

	public Product updateProduct(int id, ProductDTO productDTO) {
		Product productToUpdate = repo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

		productToUpdate.setName(productDTO.getName());
		productToUpdate.setCategory(productDTO.getCategory());
		productToUpdate.setDescription(productDTO.getDescription());
		productToUpdate.setPrice(productDTO.getPrice());
		productToUpdate.setImagePath(productDTO.getImagePath());

		return repo.save(productToUpdate);
	}

	public void deleteProduct(int id) {
		Product productToDelete = repo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
		repo.delete(productToDelete);
	}
}
