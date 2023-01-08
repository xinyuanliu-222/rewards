package com.cathy.rewards.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Response {
    private int returnCode;
    private String returnMessage;
    private List<RewardsInTotal> response;
}
