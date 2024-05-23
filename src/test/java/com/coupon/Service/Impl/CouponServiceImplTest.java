package com.coupon.Service.Impl;

import com.coupon.Entity.Coupon;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Repository.CouponRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CouponServiceImplTest {
    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    private Coupon coupon;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
         autoCloseable = MockitoAnnotations.openMocks(this);
        coupon = new Coupon();
        coupon.setId(1L);
        coupon.setName("Test Coupon");
    }

    @AfterEach
    void tearDown() throws  Exception{
        autoCloseable.close();
    }

    @Test
    void getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon);

        when(couponRepository.findAll()).thenReturn(coupons);

        List<Coupon> allCoupons = couponService.getAllCoupons();

        assertEquals(1, allCoupons.size());
        assertEquals(coupon.getId(), allCoupons.getFirst().getId());
        assertEquals(coupon.getName(), allCoupons.getFirst().getName());

//        verify(couponRepository, times(1)).findAll();
    }

    @Test
    void getCouponById() throws ResourceNotFoundException {
        when(couponRepository.findById(coupon.getId())).thenReturn(Optional.of(coupon));

        Coupon foundCoupon = couponService.getCouponById(coupon.getId());

        assertEquals(coupon.getId(),foundCoupon.getId());
        assertEquals(coupon.getName(),foundCoupon.getName());
        verify(couponRepository,times(1)).findById(coupon.getId());
    }

    @Test
    void saveCoupon() throws ResourceNotFoundException {
        when(couponRepository.save(coupon)).thenReturn(coupon);

        Coupon saveCoupon = couponService.saveCoupon(coupon);
        assertEquals(coupon.getId(),saveCoupon.getId());
        assertEquals(coupon.getName(),saveCoupon.getName());
        verify(couponRepository,times(1)).save(coupon);

    }

//    @Test
//    void deleteCouponById() throws ResourceNotFoundException {
//
//        when(couponRepository.save(coupon)).thenReturn(couponService.getCouponById(coupon.getId()));
//        couponService.deleteCouponById(coupon.getId());
//        verify(couponRepository,times(1)).deleteById(coupon.getId());
//
//    }

    @Test
    void updateCouponById() {
    }

    @Test
    void getCouponsForProduct() {
    }

    @Test
    void getCouponUsageCountForProduct() {
    }

    @Test
    void incrementCouponUsageCount() {
    }

    @Test
    void getTotalDiscount() {
    }
}