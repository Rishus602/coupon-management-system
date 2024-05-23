package com.coupon.Service.Impl;

import com.coupon.Entity.Category;
import com.coupon.Entity.Coupon;
import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Exception.UsageCountExceedException;
import com.coupon.Repository.CouponRepository;
import com.coupon.Service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class CouponServiceImpl implements CouponService {


   private static final int MAX_USAGE  = 5;

    @Autowired
    private CouponRepository couponRepository;
    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon getCouponById(Long id) throws ResourceNotFoundException {
        Optional<Coupon> coupon =  couponRepository.findById(id);
        if(coupon.isEmpty()){
            throw new ResourceNotFoundException("Coupon id is not available");
        }
        return coupon.get();
    }

//    @Override
//    public List<Coupon> getCouponsForProduct(Long id) {
//        return  couponRepository.findByProductId(id);
////        List<Coupon> coupons = couponRepository.findByProductId(id);
////        if (coupons.isEmpty()) {
////            throw new ResourceNotFoundException("No coupons found for product with id: " + id);
////        }
////        return coupons.get();
//    }

    @Override
    public Coupon saveCoupon(Coupon coupon) throws ResourceNotFoundException {
        return couponRepository.save(coupon);
    }

    @Override
    public void deleteCouponById(Long id) throws ResourceNotFoundException {
//couponRepository.deleteById(id);

        Optional<Coupon> optionalProduct = couponRepository.findById(id);
        if (optionalProduct.isPresent()) {
            couponRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Coupon with ID " + id + " not found");
        }
    }

    @Override
    public Coupon updateCouponById(Long id, Coupon coupon) {
        Coupon modelDB = couponRepository.findById(id).get();

        if (Objects.nonNull(coupon.getName()) && !"".equalsIgnoreCase(coupon.getName())) {
            modelDB.setName(coupon.getName());
        }
        if (coupon.getDiscountPercentage() != 0.0 && !"".equalsIgnoreCase(String.valueOf(coupon.getDiscountPercentage()))) {
            modelDB.setDiscountPercentage(coupon.getDiscountPercentage());
        }
        if (Objects.nonNull(coupon.getExpireDate()) && !"".equalsIgnoreCase(String.valueOf(coupon.getExpireDate()))) {
            modelDB.setExpireDate(coupon.getExpireDate());
        }
        if (Objects.nonNull(coupon.getStartDate()) && !"".equalsIgnoreCase(String.valueOf(coupon.getStartDate()))) {
            modelDB.setStartDate(coupon.getStartDate());
        }


        return couponRepository.save(modelDB);
    }

    @Override
    public List<Coupon> getCouponsForProduct(Long id) throws ResourceNotFoundException {
        Optional<Coupon> coupon= couponRepository.findById(id);
        if (coupon.isEmpty()){
            throw  new ResourceNotFoundException("coupons for product is not present");
        }
        return  couponRepository.findByProductId(id);
    }

    @Override
    public int getCouponUsageCountForProduct(String couponCode, Long productId) throws ResourceNotFoundException, UsageCountExceedException {
        Coupon coupon = couponRepository.findByCouponCodeAndProductId(couponCode, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found for the provided code and product"));
        return coupon.getUsageCount();
    }

    private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);


    @Override
    public void incrementCouponUsageCount(String couponCode, Long productId) throws ResourceNotFoundException {
        Coupon coupon = couponRepository.findByCouponCodeAndProductId(couponCode, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found for the provided code and product"));
        int usageCount = coupon.getUsageCount() + 1;
////        usageCount<MAX_USAGE;
//        coupon.setUsageCount(usageCount);
//        couponRepository.save(coupon);

        if (usageCount <= MAX_USAGE) { // Check if usageCount does not exceed MAX_USAGE
            coupon.setUsageCount(usageCount);
            couponRepository.save(coupon);
        } else {
            // Set usageCount to MAX_USAGE
            coupon.setUsageCount(MAX_USAGE);
            couponRepository.save(coupon);

            // Log a warning message
            logger.warn("Usage count exceeds maximum allowed value for coupon with code: {}", couponCode);

            // Optionally, you can throw an exception or take other appropriate actions
            // throw new IllegalStateException("Usage count exceeds maximum allowed value for coupon with code: " + couponCode);
        }

//        try {
//            Coupon coupon = couponRepository.findByCouponCodeAndProductId(couponCode, productId)
//                    .orElseThrow(() -> new ResourceNotFoundException("Coupon not found for the provided code and product"));
//
//            int usageCount = coupon.getUsageCount() + 1;
//            coupon.setUsageCount(usageCount);
//            couponRepository.save(coupon);
//        } catch (ResourceNotFoundException e) {
//            // Log the exception with the error message
//            // You can use any logging framework like SLF4J, Log4j, etc.
//            // For simplicity, we'll just print the error message to the console
//            System.err.println("Coupon not found for the provided code and product: " + e.getMessage());
//        }


//        Coupon coupon = couponRepository.findByCouponCodeAndProductId(couponCode, productId)
//                .orElse(null);
//
//        if (coupon == null) {
//            throw new ResourceNotFoundException("Coupon not found for the provided code and product");
//        }
//
//        // If the coupon is found, increment its usage count
//        int usageCount = coupon.getUsageCount() + 1;
//        coupon.setUsageCount(usageCount);
//        couponRepository.save(coupon);
    }


//    @Override
//    public Coupon getCouponByCouponCodeAndProduct(Long id, Product product) {
//        return couponRepository.findByCouponCodeAndProduct(id,product);
//    }

//    @Override
//    public Coupon redeemCoupon(Long couponId, Long productId) {
//        return null;
//    }


//    for counting the usage count
//@Override
//public Coupon redeemCoupon(Long couponId, Long productId) {
//
//    Coupon coupon = couponRepository.findById(couponId).orElse(null);
//
//    if (coupon != null && coupon.getUsageCount() < MAX_USAGE && coupon.getProduct().getId().equals(productId)) {
//        // Increment the usage count
//        coupon.setUsageCount(coupon.getUsageCount() + 1);
//
//        // Update the coupon in the database
//        couponRepository.save(coupon);
//    }
//    return coupon;
//}
    // Retrieve the coupon from the database

//    @Override
//    public Coupon saveOrUpdateCoupon(Coupon coupon) {
//        // Check if a coupon with the same code and product ID exists
//        Coupon existingCoupon = couponRepository.findByCouponCodeAndProduct(coupon.getCouponCode(), coupon.getProduct());
//
//        if (existingCoupon != null) {
//            // Increment the usage count
//            existingCoupon.setUsageCount(existingCoupon.getUsageCount() + 1);
//            return couponRepository.save(existingCoupon); // Update existing coupon
//        } else {
//            return couponRepository.save(coupon); // Save new coupon
//        }
//    }

//    @Override
//    public Coupon findByCouponCodeAndProduct(String couponCode, Product product) {
//        return couponRepository.findByCouponCodeAndProduct(couponCode, product);
//
//    }


//    @Override
//    public Coupon getRegionDiscount(Long id, String region){
//        Coupon coupon = couponRepository.findByRegion(id,region);
//
////       double regionDiscount =  coupon.getRegionDiscount();
////       double existingDiscount = coupon.getDiscountPercentage();
////       double totalDiscount = regionDiscount + existingDiscount;
//
//
//       return  couponRepository.findByRegion(id,region);
//
//    }



    @Override
    public Coupon getTotalDiscount(Long couponId, String region) throws ResourceNotFoundException{
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        if (coupon != null && region.equalsIgnoreCase(coupon.getRegion())) {
            double regionDiscount = coupon.getRegionDiscount() != null ? coupon.getRegionDiscount() : 0;

            coupon.setTotalDiscountPercentage(regionDiscount + coupon.getDiscountPercentage());
            // Calculate total discount percentage

            return couponRepository.save(coupon);
        }
//        if (coupon != null && region.equalsIgnoreCase(coupon.getRegion())) {
//            double regionDiscount = coupon.getRegionDiscount() != null ? coupon.getRegionDiscount() : 0;
//            coupon.setTotalDiscountPercentage(coupon.getDiscountPercentage() + regionDiscount);
//        } else {
//            coupon.setTotalDiscountPercentage(coupon.getDiscountPercentage());
//        }
        else {
            throw new ResourceNotFoundException("Coupon not found for ID: " + couponId + " and region: " + region);
        } // Handle appropriately if coupon or region is not found
    }





}

