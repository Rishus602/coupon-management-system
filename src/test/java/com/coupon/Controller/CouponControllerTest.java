package com.coupon.Controller;//package com.coupon.Controller;
//
//import com.coupon.Entity.Category;
//import com.coupon.Entity.Coupon;
//import com.coupon.Entity.Product;
//import com.coupon.Repository.CategoryRepository;
//import com.coupon.Repository.CouponRepository;
//import com.coupon.Repository.ProductRepository;
//import com.coupon.Service.CategoryService;
//import com.coupon.Service.CouponService;
//import com.coupon.Service.ProductService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(CouponController.class)
//class CouponControllerTest {
//@Autowired
//private  MockMvc mockMvc;
//
//@MockBean
//private CouponService couponService;
//
//
//@Autowired
//        private ProductService productService;
//
//@Autowired
//        private CategoryService categoryService;
//
//
//Coupon coupon;
//Product product;
//Category category;
//Coupon couponOne;
//Coupon couponTwo;
//List<Coupon> coupons = new ArrayList<>();
//
//
//
//
//
//
//
//    @BeforeEach
//    void setUp() throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate = dateFormat.parse("2024-05-15");
//        Date expireDate = dateFormat.parse("2024-06-01");
//
//
//
//
//        // Save Category
//        category = new Category();
//        category.setName("car");
//        category = categoryService.saveCategory(category);
//
//        product = new Product();
//        product.setName("bmw");
//        product.setCategory(category);
//        product = productService.saveProduct(product);
//
//        couponOne = new Coupon();
//        couponOne.setName("40% off discount");
//        couponOne.setDiscountPercentage(40.0);
//        couponOne.setStartDate(startDate);
//        couponOne.setExpireDate(expireDate);
//        couponOne.setProduct(product);
//        couponOne.setCouponCode("rtudj8");
//        couponOne.setRegion("chd");
//        couponOne.setRegionDiscount(5.0);
//coupons.add(couponOne);
//coupons.add(couponTwo);
//    }
//
//    @Test
//    void getAllCoupons() throws Exception {
//        when(couponService.getCouponById(couponOne.getId())).thenReturn(couponOne);
//
//        this.mockMvc.perform(get("/coupon/get/" + couponOne.getId()))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getCouponsForProduct() {
//    }
//
//    @Test
//    void createCoupon() {
//    }
//
//    @Test
//    void getTotalDiscount() {
//    }
//
//    @Test
//    void getCouponById() {
//    }
//
//    @Test
//    void getCouponUsageForProduct() {
//    }
//
//    @Test
//    void deleteCouponById() {
//    }
//
//    @Test
//    void updateCouponById() {
//    }
//
//    @Test
//    void useCouponWithProduct() {
//    }
//}

































import com.coupon.Controller.CouponController;
import com.coupon.Entity.Coupon;
import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Repository.ProductRepository;
import com.coupon.Service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CouponControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CouponService couponService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CouponController couponController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(couponController).build();
    }

//    @Test
//    void getCouponsForProduct_Success() throws Exception {
//        // Given
//        Long productId = 1L; // Set a valid productId
//        List<Coupon> coupons = Collections.singletonList(new Coupon());
//        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
//        when(couponService.getCouponsForProduct(productId)).thenReturn(coupons);
//
//        // When
//        mockMvc.perform(get("/coupon/product/{id}", productId))
//                .andExpect(status().isOk());
//
//        // Then
//        verify(productRepository, times(1)).findById(productId);
//        verify(couponService, times(1)).getCouponsForProduct(productId);
//    }


//    @Test
//    void getCouponsForProduct_Success() throws Exception {
//        // Given
//        Long productId = 1L; // Set a valid productId
//        List<Coupon> coupons = Collections.singletonList(new Coupon());
//        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
//        when(couponService.getCouponsForProduct(productId)).thenReturn(coupons);
//
//        // When
//        mockMvc.perform(get("/coupon/product/{id}", productId))
//                .andExpect(status().isOk());
//
//        // Then
//        verify(productRepository, times(1)).findById(productId);
//        verify(couponService, times(1)).getCouponsForProduct(productId);
//    }



//    @Test
//    void getTotalDiscount_Success() throws Exception {
//        // Given
//        Long couponId = 1L;
//        String region = "testRegion";
//        Coupon coupon = new Coupon();
//        when(couponService.getTotalDiscount(couponId, region)).thenReturn(coupon);
//
//        // When
//        Coupon returnedCoupon = couponController.getTotalDiscount(couponId, region);
//
//        // Then
//        assertEquals(coupon, returnedCoupon);
//        verify(couponService, times(1)).getTotalDiscount(couponId, region);
//    }


    @Test
    void getAllCoupons_Success() {
        // Given
        Coupon coupon = new Coupon();
        when(couponService.getAllCoupons()).thenReturn(Collections.singletonList(coupon));

        // When
        List<Coupon> returnedCoupons = couponController.getAllCoupons();

        // Then
        assertEquals(1, returnedCoupons.size());
        assertEquals(coupon, returnedCoupons.get(0));
        verify(couponService, times(1)).getAllCoupons();
    }


    @Test
    void getCouponById_Success() throws Exception {
        // Given
        Long couponId = 1L;
        Coupon coupon = new Coupon();
        coupon.setId(couponId);
        when(couponService.getCouponById(couponId)).thenReturn(coupon);

        // When
        Coupon returnedCoupon = couponController.getCouponById(couponId);

        // Then
        assertEquals(coupon, returnedCoupon);
        verify(couponService, times(1)).getCouponById(couponId);
    }

    @Test
    void createCoupon_Success() throws Exception {
        // Given
        Coupon coupon = new Coupon();
        when(couponService.saveCoupon(coupon)).thenReturn(coupon);

        // When
        Coupon returnedCoupon = couponController.createCoupon(coupon);

        // Then
        assertEquals(coupon, returnedCoupon);
        verify(couponService, times(1)).saveCoupon(coupon);
    }



    @Test
    void getTotalDiscount_Success() throws Exception {
        // Given
        Long couponId = 1L;
        String region = "testRegion";
        Coupon expectedCoupon = new Coupon();
        when(couponService.getTotalDiscount(couponId, region)).thenReturn(expectedCoupon);

        // When
        Coupon returnedCoupon = couponController.getTotalDiscount(couponId, region).getBody();

        // Then
        assertEquals(expectedCoupon, returnedCoupon);
        verify(couponService, times(1)).getTotalDiscount(couponId, region);
    }



}












