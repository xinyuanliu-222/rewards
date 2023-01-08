package com.cathy.rewards.controller;

import com.cathy.rewards.constant.Constant;
import com.cathy.rewards.model.*;
import com.cathy.rewards.service.RewardsService;
import com.cathy.rewards.utility.RewardsCounter;
import com.cathy.rewards.validation.RequestValidations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RewardsController.class, RequestValidations.class, RewardsService.class, RewardsCounter.class})
public class RewardsControllerTest {

    @Autowired
    private RewardsController rewardsController;

    @Test
    public void responseWithInvalidRequest() {
        Customer customer1 = Customer.builder().id(3L).name("Adam").cost(20D).month("Feb").build();
        Customer customer2 = Customer.builder().id(2L).name("Lily").cost(20D).month("M$Fghj").build();
        Customers customers = Customers.builder().customers(Arrays.asList(customer1, customer2)).build();

        ResponseEntity<Response> expectedResponse = new ResponseEntity<>(Response.builder().returnCode(102)
                .returnMessage(Constant.INVALID_MONTH).build(), HttpStatus.BAD_REQUEST);

        ResponseEntity<Response> actualResponse = rewardsController.getRewards(customers);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void responseWithValidRequest() {
        Customer customer1 = Customer.builder().id(3L).name("Adam").cost(120D).month("Feb").build();
        Customer customer2 = Customer.builder().id(2L).name("Lily").cost(70D).month("Mar").build();
        Customers customers = Customers.builder().customers(Arrays.asList(customer1, customer2)).build();

        RewardsByMonth r1 = RewardsByMonth.builder().rewards(90.0).month("Feb").build();
        RewardsByMonth r2 = RewardsByMonth.builder().rewards(20.0).month("Mar").build();
        RewardsInTotal totalRewards1 = RewardsInTotal.builder().id(3L).name("Adam").totalRewards(90.0)
                .rewardsList(Collections.singletonList(r1)).build();
        RewardsInTotal totalRewards2 = RewardsInTotal.builder().id(2L).name("Lily").totalRewards(20.0)
                .rewardsList(Collections.singletonList(r2)).build();
        Response res = Response.builder().returnCode(200).returnMessage("Success")
                .response(Arrays.asList(totalRewards2, totalRewards1)).build();
        ResponseEntity<Response> expectedResponse = new ResponseEntity<>(res, HttpStatus.ACCEPTED);

        ResponseEntity<Response> actualResponse = rewardsController.getRewards(customers);

        assertEquals(expectedResponse, actualResponse);
    }
}
