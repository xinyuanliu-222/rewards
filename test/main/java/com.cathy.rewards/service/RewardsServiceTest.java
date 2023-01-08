package com.cathy.rewards.service;

import com.cathy.rewards.model.*;
import com.cathy.rewards.utility.RewardsCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RewardsService.class, RewardsCounter.class})
public class RewardsServiceTest {

    @Autowired
    private RewardsService rewardsService;

    private Customers customers;

    @Before
    public void setUp() {
        Customer cust1 = Customer.builder().id(1L).name("Lily").month("Jan").cost(170D).build();
        Customer cust2 = Customer.builder().id(1L).name("Lily").month("Aug").cost(60D).build();
        Customer cust3 = Customer.builder().id(2L).name("Jim").month("Jan").cost(120D).build();
        Customer cust4 = Customer.builder().id(2L).name("Jim").month("Jan").cost(70D).build();
        customers = Customers.builder().customers(Arrays.asList(cust1, cust2, cust3, cust4)).build();
    }

    @Test
    public void getTotalRewardsTest() {
        RewardsByMonth r1 = RewardsByMonth.builder().rewards(190.0).month("Jan").build();
        RewardsByMonth r2 = RewardsByMonth.builder().rewards(10.0).month("Aug").build();
        RewardsByMonth r3 = RewardsByMonth.builder().rewards(110.0).month("Jan").build();
        RewardsInTotal totalRewards1 = RewardsInTotal.builder().id(1L).name("Lily").totalRewards(200.0)
                .rewardsList(Arrays.asList(r2, r1)).build();
        RewardsInTotal totalRewards2 = RewardsInTotal.builder().id(2L).name("Jim").totalRewards(110.0)
                .rewardsList(Collections.singletonList(r3)).build();
        Response expectedResponse = Response.builder().returnCode(200).returnMessage("Success")
                .response(Arrays.asList(totalRewards1, totalRewards2)).build();

        Response actualResponse = rewardsService.getAllRewards(customers);
        assertEquals(expectedResponse, actualResponse);
    }
}
