package com.coupon.Service;

import com.coupon.Entity.Category;
import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id) throws ResourceNotFoundException;
    Product saveProduct(Product product);
    void deleteProductById(Long id) throws ResourceNotFoundException;

    Product updateProductById(Long id, Product product);
}
