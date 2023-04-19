package com.example.demo.repository;
import com.example.demo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "select c from Category c where c.typeCategory = 'expense'")
    List<Category> findAllByExpense();

    @Query(value = "select c from Category c where c.typeCategory = 'income'")
    List<Category> findAllByIncome();
}
