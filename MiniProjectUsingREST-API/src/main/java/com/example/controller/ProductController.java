package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
	@Autowired
    private ProductServiceImpl productService;
	
	@GetMapping
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
	                                                    @RequestParam(defaultValue = "10") int size)
	{
	    Page<Product> products = productService.getAllProducts(page, size);
	    return ResponseEntity.ok(products);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product)
	{
	    Product createdProduct = productService.saveProduct(product);
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id)
	{
	    return productService.getProductById(id)
	            				.map(product -> ResponseEntity.ok().body(product))
	            					.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product)
	{
	    return productService.getProductById(id)
	            .map(existingProduct -> 
	            {
	                product.setId(id);
	                Product updatedProduct = productService.saveProduct(product);
	                return ResponseEntity.ok().body(updatedProduct);
	            })
	            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
	{
	    productService.deleteProduct(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
}
