package com.cathy.rewards.utility;

import org.springframework.stereotype.Component;

@Component
public class RewardsCounter {
    /**
     * This method used for counting the rewards for the given cost
     * */
    public double getRewardsByEachTransaction(double cost) {
        if (cost > 100) return (cost - 100) * 2 + 50;
        else if (cost > 50) return cost - 50;
        else return 0;
    }
}
