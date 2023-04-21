package com.example.demo.service;
import com.example.demo.Model.Category;
import java.util.List;
public interface ICrudCategory extends ICrud<Category> {
    List<Category> findAll(Long userId);
    List<Category> findAllByExpences();
    List<Category> findAllByIncome();
    Category findOne(Long id);
    void save(Category category);
    void delete(Long id);
    List<Category> findCategoryExpences(Long id);
    List<Category> findCategoryIncome(Long id);
}
