package com.cathy.rewards.utility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RewardsCounter.class)
public class RewardsCounterTest {

    @Autowired
    private RewardsCounter rewardsCounter;

    private double cost;

    @Test
    public void costOver100() {
        cost = 130;
        double actualResponse = (cost - 100) * 2 + 50;
        double expectedResponse = rewardsCounter.getRewardsByEachTransaction(cost);
        assertEquals(expectedResponse, actualResponse, 0);
    }

    @Test
    public void costUnder100Over50() {
        cost = 70;
        double actualResponse = cost - 50;
        double expectedResponse = rewardsCounter.getRewardsByEachTransaction(cost);
        assertEquals(expectedResponse, actualResponse, 0);
    }

    @Test
    public void costUnder50() {
        cost = 30;
        double actualResponse = 0;
        double expectedResponse = rewardsCounter.getRewardsByEachTransaction(cost);
        assertEquals(expectedResponse, actualResponse, 0);
    }
}
