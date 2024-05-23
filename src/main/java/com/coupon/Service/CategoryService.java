package com.coupon.Service;

import com.coupon.Entity.Category;
import com.coupon.Entity.Coupon;
import com.coupon.Exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories() throws ResourceNotFoundException;
    Category getCategoryById(Long id) throws ResourceNotFoundException;
    Category saveCategory(Category category);
    void deleteCategory(Long id) throws ResourceNotFoundException;

    Category updateCategoryById(Long id,Category category);



}
