package com.cathy.rewards.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = Customer.class)
public class CustomerTest {

    @Autowired
    private Customer customer;

    @Test
    public void nullCustomerId() {
        customer = Customer.builder().id(null).name("Adam").cost(20D).month("Feb").build();
        assertTrue(customer.nullId());
    }

    @Test
    public void noneNullCustomerId() {
        customer = Customer.builder().id(5L).name("Adam").cost(20D).month("Feb").build();
        assertFalse(customer.nullId());
    }

    @Test
    public void invalidMonth() {
        customer = Customer.builder().id(3L).name("Adam").cost(20D).month("Fbbbb").build();
        assertTrue(customer.invalidMonth());
    }

    @Test
    public void validMonth() {
        customer = Customer.builder().id(3L).name("Adam").cost(20D).month("Jun").build();
        assertFalse(customer.invalidMonth());
    }

    @Test
    public void invalidCost() {
        customer = Customer.builder().id(3L).name("Adam").cost(-20D).month("Jun").build();
        assertTrue(customer.invalidCost());
    }

    @Test
    public void validCost() {
        customer = Customer.builder().id(3L).name("Adam").cost(10D).month("Jun").build();
        assertFalse(customer.invalidCost());
    }
}
