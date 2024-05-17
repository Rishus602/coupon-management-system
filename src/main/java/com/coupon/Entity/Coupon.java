package com.coupon.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "discount_Percentage")
    private double discountPercentage;


@Column(name = "expire_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date expireDate;


    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    public Date startDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

//for couponCode
@Column(name = "coupon_code")
    private String couponCode;


//for usage of coupon
    private  int usageCount;



    // Constructor that generates the coupon code based on the discount percentage
    public Coupon(String name, double discountPercentage, Date expireDate, Date startDate, Product product) {
        this.name = name;
        this.discountPercentage = discountPercentage;
        this.expireDate = expireDate;
        this.startDate = startDate;
        this.product = product;

    }


//    for coupon generation
// Generate a random coupon code
public void generateCouponCode() {
    // Calculate the number of digits based on the discount percentage
    int numDigits = (int) (discountPercentage / 10); // For example, if discountPercentage is 50, numDigits will be 5

    // Generate the coupon code
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 10 - numDigits; i++) { // Append 10 - numDigits letters
        char randomChar = characters.charAt(random.nextInt(26)); // Random letter
        sb.append(randomChar);
    }
    for (int i = 0; i < numDigits; i++) { // Append numDigits numbers
        char randomChar = characters.charAt(random.nextInt(10) + 26); // Random number
        sb.append(randomChar);
    }
    this.couponCode = sb.toString();
}

}

