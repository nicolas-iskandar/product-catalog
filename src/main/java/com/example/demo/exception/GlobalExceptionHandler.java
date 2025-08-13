package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleNotFound(NoHandlerFoundException ex, Model model) {
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Not Found", "The requested URL was not found.");
		model.addAttribute("error", error);
		return "error";
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public String handleProductNotFound(ProductNotFoundException ex, Model model) {
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
		model.addAttribute("error", error);
		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String handleGenericError(Exception ex, Model model) {
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error", ex.getMessage());
		model.addAttribute("error", error);
		return "error";
	}
}
