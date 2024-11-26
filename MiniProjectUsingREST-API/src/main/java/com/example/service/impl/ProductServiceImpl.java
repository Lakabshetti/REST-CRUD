package com.example.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.Ps;

@Service
public class ProductServiceImpl implements Ps
{
	@Autowired
  	private ProductRepository productRepository;

	@Override
	public Page<Product> getAllProducts(int page, int size)
	{
        return productRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public Optional<Product> getProductById(Long id)
	{
		return productRepository.findById(id);
	}
	
	@Override
	public Product saveProduct(Product product)
	{
       return productRepository.save(product);
	}
	 	
	@Override
	public void deleteProduct(Long id) 
	{
        productRepository.deleteById(id);
	}
}
