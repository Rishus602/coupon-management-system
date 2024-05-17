package com.coupon.Exception;




public class UsageCountExceedException extends RuntimeException {
    public UsageCountExceedException(String message) {
        super(message);
    }
}