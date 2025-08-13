package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

@Controller
public class ProductController {
	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Product> products = service.getProducts();
		model.addAttribute("products", products);
		return "index";
	}

	@GetMapping("/products/{id}")
	public String getProduct(@PathVariable int id, Model model) {
		Product product = service.getProductById(id);
		model.addAttribute("product", product);
		return "product";
	}

	@GetMapping("/products/new")
	public String showCreateForm(Model model) {
		model.addAttribute("product", new ProductDTO());
		return "create-product";
	}

	@PostMapping("/products")
	public String createProduct(ProductDTO productDTO) {
		service.createProduct(productDTO);
		return "redirect:/";
	}

	@GetMapping("/products/{id}/edit")
	public String showUpdateForm(@PathVariable int id, Model model) {
		Product product = service.getProductById(id);
		ProductDTO dto = new ProductDTO(product.getName(), product.getCategory(), product.getDescription(),
				product.getPrice(), product.getImagePath());
		model.addAttribute("product", dto);
		model.addAttribute("id", id);
		return "update-product";
	}

	@PostMapping("/products/{id}/edit")
	public String updateProduct(@PathVariable int id, ProductDTO productDTO) {
		service.updateProduct(id, productDTO);
		return "redirect:/products/" + id;
	}

	@PostMapping("/products/{id}/delete")
	public String deleteProduct(@PathVariable int id) {
		service.deleteProduct(id);
		return "redirect:/";
	}
}
