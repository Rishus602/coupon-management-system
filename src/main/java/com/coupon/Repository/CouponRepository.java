package com.coupon.Repository;

import com.coupon.Entity.Coupon;
import com.coupon.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    public List<Coupon> findByProductId(Long id);


    Optional<Coupon> findByCouponCodeAndProductId(String couponCode, Long productId );




//    Coupon findByRegion(Long id, String region);
//    Coupon findByCouponCodeAndProduct(Long id, Product product);

}
