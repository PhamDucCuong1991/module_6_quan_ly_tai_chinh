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
    public List<Category> findAll(Long userId) {
        return categoryRepository.findCById(userId);
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

    @Override
    public List<Category> findAllByIncome() {
        return categoryRepository.findAllByIncome();
    }

    @Override
    public List<Category> findAllByExpences() {
        return categoryRepository.findAllByExpense();
    }
    @Override
    public List<Category> findCategoryExpences(Long id) {
        return categoryRepository.selectCategoryExByUserId(id);
    }
    @Override
    public List<Category> findCategoryIncome(Long id) {
        return categoryRepository.selectCategoryInByUserId(id);
    }

    @Override
    public List<Category> findCategoryExpencesOnlyUserId(Long id) {
        return categoryRepository.selectCategoryExByOnlyUserId(id);
    }

    @Override
    public List<Category> findCategoryIncomeOnlyUserId(Long id) {
        return categoryRepository.selectCategoryInByOnlyUserId(id);
    }

    @Override
    public List<Category> findCategoryExpencesDefault() {
        return categoryRepository.selectCategoryExDefault();
    }

    @Override
    public List<Category> findCategoryIncomeDefault() {
        return categoryRepository.selectCategoryInDefault();
    }
}
