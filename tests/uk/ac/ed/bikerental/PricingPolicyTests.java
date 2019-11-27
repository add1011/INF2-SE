package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collection;


public class PricingPolicyTests {
    // You can add attributes here
    NormalPricingPolicy normalPricingPolicy1;
    DiscountPolicy discountPolicy1;
    private Collection<Bike> bikes = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception {
        NormalPricingPolicy normalpolicy1 = new NormalPricingPolicy();
        DiscountPolicy discountPolicy1 = new DiscountPolicy();
    }
    @Test
    void testNormalPricingPolicy(){

    }
    
    // TODO: Write tests for pricing policies
}