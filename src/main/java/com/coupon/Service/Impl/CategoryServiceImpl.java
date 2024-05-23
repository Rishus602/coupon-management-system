package com.coupon.Service.Impl;

import com.coupon.Entity.Category;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Repository.CategoryRepository;
import com.coupon.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() throws ResourceNotFoundException {
        List<Category> category = categoryRepository.findAll();
        if (category.isEmpty()){
            throw new ResourceNotFoundException("Something wrong with url");
        }

        return categoryRepository.findAll();
//        try {
//            List<Category> categories = categoryRepository.findAll();
//            if (categories.isEmpty()) {
//                throw new ResourceNotFoundException("No categories found");
//            }
//            return categories;
//        } catch (HttpClientErrorException.NotFound | HttpServerErrorException.BadGateway ex) {
//            // Handle 404 Not Found error specifically
//            throw new ResourceNotFoundException("The requested resource was not found. Detail: " + ex.getMessage());
//        } catch (ResourceNotFoundException ex) {
//            // Handle ResourceNotFoundException
//            throw ex;
//        } catch (Exception ex) {
//            // Handle other exceptions generically
//            throw new ResourceNotFoundException("An error occurred while processing the request: " + ex.getMessage());
//        }
    }

    @Override
    public Category getCategoryById(Long id) throws ResourceNotFoundException {
        Optional<Category> category =  categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new ResourceNotFoundException("Resource id is not available");
        }
        return category.get();
    }



    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) throws ResourceNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("Category id is deleted / the id is wrong");
        }
        categoryRepository.deleteById(id);
    }


//    @Override
//    public Category updateCategory(Category category, Long id) {
//        Category existingCategory = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Not found with id" + id));
//
//        // Check if the provided category object is not null
//        if (category != null) {
//            // Update the existing category's name if the provided category's name is not null
//            if (category.getName() != null) {
//                existingCategory.setName(category.getName());
//            }
//            // Add more fields to update if necessary
//        }
//
//        try {
//            // Save the updated category back to the repository
//            return categoryRepository.save(existingCategory);
//        } catch (Exception e) {
//            // Handle database constraint violations or other runtime exceptions
//            throw new RuntimeException("Failed to update category with id: " + id, e);
//        }
//        if (category != null) {
//            existingCategory.setName(category.getName());
//        }
        // Save the updated category back to the repository and handle the possibility of null return
//        Category updatedCategory = categoryRepository.save(existingCategory);
//        return categoryRepository.save(existingCategory);
//    }
    public Category updateCategoryById( Long id, Category category){

        Category modelDB = categoryRepository.findById(id).get();

        if(Objects.nonNull(category.getName()) &&! "".equalsIgnoreCase(category.getName())){
            modelDB.setName(category.getName());
        }

//        if(Objects.nonNull(category.getName()) &&! "".equalsIgnoreCase(category.getName())){
//            modelDB.setName(category.getName());
//        }


//        if(Objects.nonNull(model.getDepartmentAddress()) &&! "".equalsIgnoreCase(model.getDepartmentName())){
//            modelDB.setDepartmentAddress(model.getDepartmentAddress());
//        }

        return categoryRepository.save(modelDB);

    }
}
