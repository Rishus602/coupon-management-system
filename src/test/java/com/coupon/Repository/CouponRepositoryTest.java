package com.coupon.Repository;


import com.coupon.Entity.Category;
import com.coupon.Entity.Coupon;
import com.coupon.Entity.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CouponRepositoryTest {

    @Autowired
    private  CouponRepository couponRepository;

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private  CategoryRepository categoryRepository;
    private Coupon coupon;
    private Category category;

    private Product product;



    // June 1, 2024

    public CouponRepositoryTest() throws ParseException {
    }


    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2024-05-15");
        Date expireDate = dateFormat.parse("2024-06-01");




        // Save Category
        category = new Category();
        category.setName("car");
        category = categoryRepository.save(category);

        product = new Product();
        product.setName("bmw");
        product.setCategory(category);
        product = productRepository.save(product);

        coupon = new Coupon();
        coupon.setName("40% off discount");
        coupon.setDiscountPercentage(40.0);
        coupon.setStartDate(startDate);
        coupon.setExpireDate(expireDate);
        coupon.setProduct(product);
        coupon.setCouponCode("rtudj8");
        coupon.setRegion("chd");
        coupon.setRegionDiscount(5.0);
        coupon = couponRepository.save(coupon);


//         category = new Category(1L, "car");
//        categoryRepository.save(category);
//
//        product = new Product(1L, "bmw", category);
//        productRepository.save(product);
//
//
//        coupon = new Coupon(1L,
//                "40% off discount",
//                40.0,
//                startDate,
//                expireDate,
//                new Product(1L,"bmw",new Category(1L,"car")),
//
//                "rtudj8",
//                3,
//                45.0,
//                "chd",
//                5.0);
//        couponRepository.save(coupon);
    }


//    @AfterAll
//    static void tearDown() {
////coupon= null;
////couponRepository.deleteAll();
//        couponRepository.deleteAll();
//        productRepository.deleteAll();
//        categoryRepository.deleteAll();
//    }


    @Test
    public void testFindByCouponId(){
//       Optional< Coupon> foundCoupon =couponRepository.findById(coupon.getId());
//       assertTrue(foundCoupon.isPresent());
//        assertEquals(coupon.getId(), foundCoupon.get().getId());
//        assertEquals(coupon.getName(), foundCoupon.get().getName());
//        assertEquals(coupon.getDiscountPercentage(), foundCoupon.get().getDiscountPercentage());
//        assertEquals(coupon.getCouponCode(),foundCoupon.get().getCouponCode());
//        assertEquals(coupon.getRegionDiscount(),foundCoupon.get().getRegionDiscount());
//        assertEquals(coupon.getRegion(),foundCoupon.get().getRegion());
        // Add more assertions as needed to verify all properties

        Optional<Coupon> foundCoupon = couponRepository.findById(coupon.getId());
        Optional<Product> foundProduct = productRepository.findById(product.getId());
        Optional<Category> categoryProduct = categoryRepository.findById(category.getId());
        assertTrue(foundCoupon.isPresent());
        assertTrue(foundProduct.isPresent());
        assertTrue((categoryProduct.isPresent()));
        assertEquals(coupon.getId(), foundCoupon.get().getId());
        assertEquals(coupon.getName(), foundCoupon.get().getName());
        assertEquals(coupon.getDiscountPercentage(), foundCoupon.get().getDiscountPercentage());
        assertEquals(coupon.getCouponCode(), foundCoupon.get().getCouponCode());
        assertEquals(coupon.getRegionDiscount(), foundCoupon.get().getRegionDiscount());
        assertEquals(coupon.getRegion(), foundCoupon.get().getRegion());
    }





    @Test
    public void testFindByCouponCodeAndProductId() {
//        Optional<Coupon> foundCoupon = couponRepository.findByCouponCodeAndProductId(coupon.getCouponCode(), product.getId());
////        assertTrue(foundCoupon.isPresent());
//        assertEquals(coupon.getId(), foundCoupon.get().getId());
//        assertEquals(product.getId(), foundCoupon.get().getProduct().getId());
        // Add more assertions as needed

        Optional<Coupon> foundCouponOptional = couponRepository.findByCouponCodeAndProductId(coupon.getCouponCode(), product.getId());
        assertTrue(foundCouponOptional.isPresent(), "Coupon not found");
        Coupon foundCoupon = foundCouponOptional.get();

        assertEquals(coupon.getId(), foundCoupon.getId());
        assertEquals(product.getId(), foundCoupon.getProduct().getId());
    }


    @Test
    public void testFindByProductId() {
        List<Coupon> foundCoupons = couponRepository.findByProductId(product.getId());
//        assertTrue(foundCoupons.isEmpty(), "coupon id not found");
        assertEquals(1, foundCoupons.size());
        Coupon foundCoupon = foundCoupons.getFirst();
        assertEquals(coupon.getId(), foundCoupon.getId());


    }
}
