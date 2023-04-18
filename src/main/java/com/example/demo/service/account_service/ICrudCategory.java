package com.example.demo.service.account_service;
import com.example.demo.Model.Category;

import java.util.List;
public interface ICrudCategory {
    List<Category> findAll();
    Category findOne(Long id);
    void save(Category category);
    void delete(Long id);
}
