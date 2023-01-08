package com.cathy.rewards.controller;

import com.cathy.rewards.model.Customers;
import com.cathy.rewards.model.Response;
import com.cathy.rewards.service.RewardsService;
import com.cathy.rewards.validation.RequestValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsController {

    private final RewardsService rewardsService;
    private final RequestValidations requestValidations;

    @Autowired
    public RewardsController(final RewardsService rewardsService, final RequestValidations requestValidations) {
        this.rewardsService = rewardsService;
        this.requestValidations = requestValidations;
    }

    @PostMapping("/rewards")
    public ResponseEntity<Response> getRewards(@RequestBody Customers customers) {
        Response response = requestValidations.invalidRequestResponse(customers);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response = rewardsService.getAllRewards(customers);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
