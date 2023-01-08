package com.cathy.rewards.model;

import com.cathy.rewards.constant.Constant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("month")
    private String month;

    public boolean nullId() {
        return this.id == null;
    }

    public boolean invalidMonth() {
        return this.month == null || !Constant.MONTHS_SET.contains(this.month);
    }
    public boolean invalidCost() {
        return this.cost == null || this.cost < 0;
    }
}
