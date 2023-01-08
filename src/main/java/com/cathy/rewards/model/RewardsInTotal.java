package com.cathy.rewards.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RewardsInTotal {
    private Long id;
    private String name;
    private Double totalRewards;
    private List<RewardsByMonth> rewardsList;
}
