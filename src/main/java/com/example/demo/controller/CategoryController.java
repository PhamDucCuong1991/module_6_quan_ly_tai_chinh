package com.example.demo.controller;
import com.example.demo.Model.Category;
import com.example.demo.service.account_service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;
    @GetMapping()
    public ResponseEntity<List<Category>> findAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<Category> findOne(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.findOne(id),HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<Void> create(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Category category){
        Category category1=categoryService.findOne(id);
        if (category1!=null){
            categoryService.save(category);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
