package com.example.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.entity.Product;

public interface Ps
{
	 Page<Product> getAllProducts(int page, int size);
	 
	 Optional<Product> getProductById(Long id);
	 
	 Product saveProduct(Product product);
	 
	 void deleteProduct(Long id);
}
