package com.cathy.rewards.service;

import com.cathy.rewards.model.*;
import com.cathy.rewards.utility.RewardsCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardsService {

    private final RewardsCounter  rewardsCounter;

    @Autowired
    public RewardsService(final RewardsCounter rewardsCounter) {
        this.rewardsCounter = rewardsCounter;
    }

    /**
     * This method is used for count rewards for the same person in same month, and then calculate the total rewards
     * After calculating all the customers' reward, will generate the response
     * */
    public Response getAllRewards(Customers customers) {
        Map<Long, List<RewardsByEachTransaction>> map = new HashMap<>();
        Map<Long, String> userMap = new HashMap<>();
        //group customers by id and creating another map for map the id and name
        for (Customer customer: customers.getCustomers()) {
            long id = customer.getId();
            map.putIfAbsent(id, new ArrayList<>());
            map.get(id).add(RewardsByEachTransaction.builder().month(customer.getMonth())
                    .rewards(rewardsCounter.getRewardsByEachTransaction(customer.getCost())).build());
            userMap.put(id, customer.getName());
        }

        //calculate the total rewards for one customer in same month and his/hers total rewards
        List<RewardsInTotal> rewardsInTotalList = new ArrayList<>();
        for (long id: map.keySet()) {
            List<RewardsByEachTransaction> rewardsByEachTransactions = map.get(id);

            Map<String, Double> rewardsMap = new HashMap<>();
            double total = 0.0;
            for (RewardsByEachTransaction reward: rewardsByEachTransactions) {
                String month = reward.getMonth();
                double rewards = reward.getRewards();
                rewardsMap.put(month, rewardsMap.getOrDefault(month, 0.0) + rewards);
                total += rewards;
            }

            List<RewardsByMonth> rewardsByMonths = new ArrayList<>();
            for (String month: rewardsMap.keySet()) {
                rewardsByMonths.add(RewardsByMonth.builder().month(month).rewards(rewardsMap.get(month)).build());
            }

            RewardsInTotal rewardsInTotal = RewardsInTotal.builder().id(id).name(userMap.get(id)).totalRewards(total)
                    .rewardsList(rewardsByMonths).build();
            rewardsInTotalList.add(rewardsInTotal);
        }

        return Response.builder().returnCode(200).returnMessage("Success").response(rewardsInTotalList).build();
    }
}
