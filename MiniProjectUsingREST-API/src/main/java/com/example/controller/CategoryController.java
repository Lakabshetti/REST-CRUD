package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.entity.Category;
import com.example.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoryController 
{
	@Autowired
    private CategoryServiceImpl categoryService;
	
	@GetMapping
    public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page,
    									@RequestParam(defaultValue = "10") int size)
    {
    	Page<Category> categories = categoryService.getAllCategories(page, size);
        return ResponseEntity.ok(categories);
    }

    
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
        Category createdCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id)
    {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) 
        {
            return ResponseEntity.ok(category.get());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category)
    {
        Optional<Category> existingCategory = categoryService.getCategoryById(id);
        if (existingCategory.isPresent())
        {
            category.setId(id);
            Category updatedCategory = categoryService.saveCategory(category);
            return ResponseEntity.ok(updatedCategory);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
    {
        Optional<Category> existingCategory = categoryService.getCategoryById(id);
        if (existingCategory.isPresent())
        {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
