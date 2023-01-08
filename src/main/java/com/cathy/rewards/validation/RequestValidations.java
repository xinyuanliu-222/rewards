package com.cathy.rewards.validation;

import com.cathy.rewards.constant.Constant;
import com.cathy.rewards.model.Customer;
import com.cathy.rewards.model.Customers;
import com.cathy.rewards.model.Response;
import org.springframework.stereotype.Component;

@Component
public class RequestValidations {
    public Response invalidRequestResponse(Customers customers) {
        Response response = null;

        for (Customer customer: customers.getCustomers()) {
            if (customer.nullId()) {
                response = Response.builder().returnCode(101).returnMessage(Constant.EMPTY_ID).build();
                break;
            }

            if (customer.invalidMonth()) {
                response = Response.builder().returnCode(102).returnMessage(Constant.INVALID_MONTH).build();
                break;
            }

            if (customer.invalidCost()) {
                response = Response.builder().returnCode(103).returnMessage(Constant.INVALID_COST).build();
                break;
            }
        }
        return response;
    }
}
