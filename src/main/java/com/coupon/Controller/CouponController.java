package com.coupon.Controller;

import com.coupon.Entity.Coupon;
import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Exception.UsageCountExceedException;
import com.coupon.Repository.CouponRepository;
import com.coupon.Repository.ProductRepository;
import com.coupon.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Autowired
    private ProductRepository productRepository;


    // Get all coupons
    @GetMapping("/getAll")
    public List<Coupon> getAllCoupons() {

        return couponService.getAllCoupons();
    }





    // Get all coupons for a product by product ID
    @GetMapping("/product/{id}")
    public ResponseEntity<List<Coupon>> getCouponsForProduct(@PathVariable Long id) throws ResourceNotFoundException{



        Product ref = productRepository.findById(id).get();

        List<Coupon> coupons = couponService.getCouponsForProduct(ref.getId());
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    // Create a new coupon
    @PostMapping("/add")
    public Coupon createCoupon(@RequestBody Coupon coupon) {

 coupon.generateCouponCode();
  String cpn = coupon.getCouponCode();
   coupon.setCouponCode(cpn);

        return couponService.saveCoupon(coupon);
    }



//        get by id
@GetMapping("/get/{id}")
public Coupon getCouponById(@PathVariable Long id) throws ResourceNotFoundException {
    return couponService.getCouponById(id);
}


//         delete by id

    @DeleteMapping("/delete/{id}")
    public String deleteCouponById(@PathVariable Long id) throws ResourceNotFoundException{
         couponService.deleteCouponById(id);
         return "Coupon Deleted Successfully";
    }

//        put by id

//    @PutMapping("/update/{id}")
//    public Coupon updateCouponById( @RequestBody Coupon coupon, @PathVariable Long id){
//        Coupon existingCoupon = couponService.getCouponById(coupon,id);
//        if (existingCoupon != null){
//            couponService.updateCouponById()
//        }
//    }


//    update by id
    @PutMapping("/update/{id}")
    public Coupon updateCouponById(@PathVariable Long id, @RequestBody Coupon coupon){
        return couponService.updateCouponById(id,coupon);
    }




//this get is used for no. of usage of coupon
    @GetMapping("/usage/{couponCode}/product/{productId}")
    public ResponseEntity<Integer> getCouponUsageForProduct(@PathVariable String couponCode, @PathVariable Long productId) {
        try {
            int usageCount = couponService.getCouponUsageCountForProduct(couponCode, productId);
            return ResponseEntity.ok(usageCount);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


//    this post method is used for increment the coupon usage related to product

    @PostMapping("/use/{couponCode}/product/{productId}")
    public ResponseEntity<String> useCouponWithProduct(@PathVariable String couponCode, @PathVariable Long productId) throws  UsageCountExceedException {
//        try {
//            couponService.incrementCouponUsageCount(couponCode, productId);
//            return ResponseEntity.ok("Coupon usage count incremented successfully");
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
        try {
            // Attempt to increment the coupon usage count
            couponService.incrementCouponUsageCount(couponCode, productId);

            // If successful, return a success message
            return ResponseEntity.ok("Coupon usage count incremented successfully");
        } catch (ResourceNotFoundException e) {
            // If coupon not found, return a 404 (Not Found) response
            return ResponseEntity.notFound().build();
        } catch (UsageCountExceedException ex) {
            // If usage count exceeds maximum limit, return a warning message
            String warningMessage = "Usage count exceeds maximum allowed value for coupon with code: " + couponCode;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(warningMessage);
        }
    }




















//    new method for usageCount

//    @PostMapping("/save-or-update")
//    public ResponseEntity<Coupon> saveOrUpdateCoupon(@RequestBody Coupon coupon) {
//
////        coupon.generateCouponCode();
////        String cpn = coupon.getCouponCode();
////        coupon.setCouponCode(cpn);
////        Coupon savedCoupon = couponService.saveOrUpdateCoupon(coupon);
////        return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
//        Coupon existingCoupon = couponService.findByCouponCodeAndProduct(coupon.getCouponCode(), coupon.getProduct());
//
//        if (existingCoupon != null) {
//            // Increment the usage count
//            existingCoupon.setUsageCount(existingCoupon.getUsageCount() + 1);
//            couponService.saveOrUpdateCoupon(existingCoupon); // Update existing coupon
//            return new ResponseEntity<>(existingCoupon, HttpStatus.CREATED);
//        } else {
//            coupon.generateCouponCode(); // Generate coupon code only if it's a new coupon
//            Coupon savedCoupon = couponService.saveOrUpdateCoupon(coupon); // Save new coupon
//            return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
//        }
//    }
}