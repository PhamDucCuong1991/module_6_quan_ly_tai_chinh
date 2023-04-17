package com.example.demo.service.account_service.impl;
import com.example.demo.Model.Category;
import com.example.demo.repository.CategoryRepository;

import com.example.demo.service.account_service.ICrudCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICrudCategory {
    @Autowired
    public CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findOne(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    @Override
    public void save(Category category) {
    categoryRepository.save(category);
    }
    @Override
    public void delete(Long id) {
    categoryRepository.deleteById(id);
    }
}
