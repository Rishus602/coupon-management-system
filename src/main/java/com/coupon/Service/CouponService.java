package com.coupon.Service;

import com.coupon.Entity.Coupon;
import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Exception.UsageCountExceedException;

import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupons();
    Coupon getCouponById(Long id) throws ResourceNotFoundException;
    Coupon saveCoupon(Coupon coupon) throws ResourceNotFoundException;
    void deleteCouponById(Long id) throws ResourceNotFoundException;

    Coupon updateCouponById(Long id, Coupon coupon);


//    new method by product id ---> custom method

    List<Coupon> getCouponsForProduct(Long id) throws ResourceNotFoundException;



    int getCouponUsageCountForProduct(String couponCode, Long productId) throws ResourceNotFoundException;

    void incrementCouponUsageCount(String couponCode, Long productId) throws ResourceNotFoundException , UsageCountExceedException;


//   Coupon getRegionDiscount(Long id, String region);



Coupon getTotalDiscount(Long couponId, String region) throws ResourceNotFoundException;


}
