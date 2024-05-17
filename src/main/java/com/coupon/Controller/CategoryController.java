package com.coupon.Controller;


import com.coupon.Entity.Category;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
private CategoryService categoryService;


    // Create a new category
    @PostMapping("/add")
    public Category createCategory(@RequestBody Category category) {
//        Category savedCategory = categoryService.saveCategory(category);
//        return new (savedCategory, HttpStatus.CREATED) {
        return categoryService.saveCategory(category);
        }


//        getting all category

        @GetMapping("/getAll")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategories();
    }


//    get category by id

    @GetMapping("/get/{id}")
    public Category getCategoryById(@PathVariable Long id) throws ResourceNotFoundException {
        return categoryService.getCategoryById(id);
    }


//    delete a category
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category deleted Successfully!";
    }



//    update a category
    @PutMapping("/update/{id}")
    public Category updateCategoryById(@PathVariable("id") Long id, @RequestBody Category category){
        return categoryService.updateCategoryById(id,category);
    }
    }
