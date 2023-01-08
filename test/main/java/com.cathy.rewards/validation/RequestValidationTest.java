package com.cathy.rewards.validation;

import com.cathy.rewards.constant.Constant;
import com.cathy.rewards.model.Customer;
import com.cathy.rewards.model.Customers;
import com.cathy.rewards.model.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RequestValidations.class)
public class RequestValidationTest {

    @Autowired
    private RequestValidations requestValidations;

    @Test
    public void invalidRequestWithNullId() {
        Customer customer1 = Customer.builder().id(null).name("Adam").cost(20D).month("Feb").build();
        Customer customer2 = Customer.builder().id(2L).name("Lily").cost(20D).month("Feb").build();
        Customers customers = Customers.builder().customers(Arrays.asList(customer1, customer2)).build();

        Response expectedResponse = Response.builder().returnCode(101).returnMessage(Constant.EMPTY_ID).build();

        Response actualResponse = requestValidations.invalidRequestResponse(customers);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void invalidRequestWithInvalidMonth() {
        Customer customer1 = Customer.builder().id(3L).name("Adam").cost(20D).month("Feb").build();
        Customer customer2 = Customer.builder().id(2L).name("Lily").cost(20D).month("M$Fghj").build();
        Customers customers = Customers.builder().customers(Arrays.asList(customer1, customer2)).build();

        Response expectedResponse = Response.builder().returnCode(102).returnMessage(Constant.INVALID_MONTH).build();

        Response actualResponse = requestValidations.invalidRequestResponse(customers);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void invalidRequestWithInvalidCost() {
        Customer customer1 = Customer.builder().id(3L).name("Adam").cost(-90D).month("Feb").build();
        Customer customer2 = Customer.builder().id(2L).name("Lily").cost(20D).month("Sep").build();
        Customers customers = Customers.builder().customers(Arrays.asList(customer1, customer2)).build();

        Response expectedResponse = Response.builder().returnCode(103).returnMessage(Constant.INVALID_COST).build();

        Response actualResponse = requestValidations.invalidRequestResponse(customers);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void invalidRequestWithValidRequest() {
        Customer customer1 = Customer.builder().id(3L).name("Adam").cost(20D).month("Feb").build();
        Customer customer2 = Customer.builder().id(2L).name("Lily").cost(20D).month("Mar").build();
        Customers customers = Customers.builder().customers(Arrays.asList(customer1, customer2)).build();

        Response actualResponse = requestValidations.invalidRequestResponse(customers);

        assertNull(actualResponse);
    }
}
