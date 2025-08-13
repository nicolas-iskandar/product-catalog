package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Category is required")
	private String category;

	private String description;

	@Positive(message = "Price must be positive")
	private double price;

	private String imagePath;
}
