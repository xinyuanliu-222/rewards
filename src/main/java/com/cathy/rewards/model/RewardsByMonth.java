package com.cathy.rewards.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardsByMonth {
    private String month;
    private Double rewards;
}
