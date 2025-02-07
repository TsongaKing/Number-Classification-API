package com.devops.numberclassification.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
    "number",
    "isInteger",
    "is_prime",
    "is_perfect",
    "properties",
    "digit_sum",
    "fun_fact"
})
public class NumberResponse {
    private double number;
    private boolean isInteger;

    @JsonProperty("is_prime")
    private Boolean isPrime;

    @JsonProperty("is_perfect")
    private Boolean isPerfect;

    private List<String> properties;

    @JsonProperty("digit_sum")
    private Integer digitSum;

    @JsonProperty("fun_fact")
    private String funFact;
}