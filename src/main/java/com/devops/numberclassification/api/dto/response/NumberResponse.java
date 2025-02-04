package com.devops.numberclassification.api.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty; // Ensure proper mapping to snake_case

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumberResponse {
    private int number;

    @JsonProperty("is_prime") // Ensure snake_case in JSON response
    private boolean isPrime;

    @JsonProperty("is_perfect") // Ensure snake_case in JSON response
    private boolean isPerfect;

    private List<String> properties;

    @JsonProperty("digit_sum") // Ensure snake_case in JSON response
    private int digitSum;

    @JsonProperty("fun_fact") // Ensure snake_case in JSON response
    private String funFact;
}
