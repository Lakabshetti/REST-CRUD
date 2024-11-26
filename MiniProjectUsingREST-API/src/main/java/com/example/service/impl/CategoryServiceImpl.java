package com.example.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import com.example.service.Cs;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImpl implements Cs
{
	 	@Autowired
	    private CategoryRepository categoryRepository;

	 	@Override
	    public Page<Category> getAllCategories(int page, int size)
	 	{
	        return categoryRepository.findAll(PageRequest.of(page, size));
	    }
	 	
	    @Override
	    public Optional<Category> getCategoryById(Long id)
	    {
	        return categoryRepository.findById(id);
	    }

		@Override
		public Category saveCategory(Category category)
		{
   	        return categoryRepository.save(category);
		}
		
		@Override
	    public Category updateCategory(Long id, Category category)
		{
	        if (categoryRepository.existsById(id)) 
	        {
	            category.setId(id);
//	            category.setName(category.getName());
	            return categoryRepository.save(category);
	        }
	        else
	        {
	            throw new EntityNotFoundException("Category not found with id " + id);
	        }
	    }

		@Override
		public void deleteCategory(Long id)
		{
	        categoryRepository.deleteById(id);
		}
}
