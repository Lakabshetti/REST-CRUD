package com.example.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.entity.Category;

public interface Cs
{
	 Page<Category> getAllCategories(int page, int size);
	
	 Optional<Category> getCategoryById(Long id);
	
	 Category saveCategory(Category category);
	 
	 Category updateCategory(Long id, Category category);
	 
	 void deleteCategory(Long id);
}
