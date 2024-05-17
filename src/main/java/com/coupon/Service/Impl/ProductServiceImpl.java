package com.coupon.Service.Impl;

import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Repository.ProductRepository;
import com.coupon.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ResourceNotFoundException {
        Optional<Product> product =  productRepository.findById(id);
        if (product.isEmpty()){
            throw  new ResourceNotFoundException("Product id is not available");
        }
        return product.get();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
//    public void deleteProductById(Long id) {
//productRepository.deleteById(id);
//    }
public void deleteProductById(Long id) throws ResourceNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Product with ID " + id + " not found");
        }
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        Product modelDB = productRepository.findById(id).get();

        if(Objects.nonNull(product.getName()) &&! "".equalsIgnoreCase(product.getName())){
            modelDB.setName(product.getName());
        }

//        this is used for changing the data in the json object
//        if (Objects.nonNull((product.getCategory().getName())) &&! "".equalsIgnoreCase(product.getCategory().getName())){
//            modelDB.setCategory(product.getCategory().getName());
//        }
        return productRepository.save(modelDB);
    }
}
