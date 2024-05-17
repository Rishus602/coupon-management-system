package com.coupon.Controller;

import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    // Create a new product
    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) {
//        Product savedProduct = productService.saveProduct(product);
//        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
       return productService.saveProduct(product);
    }

//        getting all the details
// Get all products
@GetMapping("/getAll")
public List<Product> getAllProducts() {
//    List<Product> products = productService.getAllProducts();
//    return new ResponseEntity<>(products, HttpStatus.OK);
    return productService.getAllProducts();
}

//for getById

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable Long id) throws ResourceNotFoundException {
        return productService.getProductById(id);
    }


//    for delete by id

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) throws ResourceNotFoundException {

        productService.deleteProductById(id);
        return "Product Deleted Successfully ";

    }


//    public ResponseEntity<String> deleteProductById(@PathVariable Long id) throws ResourceNotFoundException {





//    update by id
//    @PutMapping("/update/{id}")
//    public Product updateProductById(@PathVariable Long id){
//        Product existingProduct = productService.getProductById(id);
//        if (existingProduct != null){
//            existingProduct.setCategory();
//        }
//    }

//@GetMapping("/getAll")
//public ResponseEntity<List<Product>> getAllProducts() {
//    try {
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }
//}


//    update by id

    @PutMapping("/update/{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProductById(id,product);
    }
}
