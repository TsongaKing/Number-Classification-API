package com.devops.numberclassification.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List; 

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumberResponse {
    private int number;

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