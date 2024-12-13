package com.example.Spring_LAB_5.service;

import com.example.Spring_LAB_5.entity.Category;
import com.example.Spring_LAB_5.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found with id: " + id));
    }
}