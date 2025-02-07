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
    "is_prime",
    "is_perfect",
    "properties",
    "digit_sum",
    "fun_fact"
}

)
public class NumberResponse {
    private double number; // Change from int to double

    @JsonProperty("is_prime")
    private boolean isPrime;

    @JsonProperty("is_perfect")
    private boolean isPerfect;

    private List<String> properties;

    @JsonProperty("digit_sum")
    private int digitSum;

    @JsonProperty("fun_fact")
    private String funFact;
}