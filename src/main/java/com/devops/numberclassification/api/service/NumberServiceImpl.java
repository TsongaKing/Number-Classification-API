package com.devops.numberclassification.api.service;

import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.util.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Slf4j
@Service
public class NumberServiceImpl implements NumberServiceInterface {

    private final RestTemplate restTemplate;

    public NumberServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
public NumberResponse classifyNumber(double number) {
    boolean isInteger = (number == Math.floor(number)) && !Double.isInfinite(number);
    int integerValue = (int) number;

    NumberResponse.NumberResponseBuilder builder = NumberResponse.builder()
        .number(isInteger ? integerValue : number) // Remove .0 for integers
        .isPrime(null)
        .isPerfect(null)
        .digitSum(null);

    if (isInteger) {
        List<String> properties = new ArrayList<>();
        // Sign property
        properties.add(integerValue < 0 ? "negative" : "positive");
        // Even/odd
        properties.add(NumberUtils.isEven(integerValue) ? "even" : "odd");
        // Special numbers
        if (NumberUtils.isArmstrong(integerValue)) {
            properties.add("armstrong");
        }
        
        builder.isPrime(NumberUtils.isPrime(integerValue))
               .isPerfect(NumberUtils.isPerfect(integerValue))
               .properties(properties)
               .digitSum(NumberUtils.digitSum(integerValue))
               .funFact(fetchFunFact(integerValue));
    } else {
        builder.properties(List.of("non-integer"))
               .funFact("Non-integer numbers cannot be classified as prime, perfect, or Armstrong.");
    }

    return builder.build();
}

private String fetchFunFact(int number) {
    if (NumberUtils.isArmstrong(number)) {
        String digits = String.valueOf(Math.abs(number))
                           .replace("", " ")
                           .trim()
                           .replace(" ", "^" + String.valueOf(number).length() + " + ");
        return number + " is an Armstrong number because " + digits + "= " + Math.abs(number);
    }
    try {
        String url = "http://numbersapi.com/" + number + "/math?json";
        var response = restTemplate.getForEntity(url, Map.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().get("text").toString();
        }
    } catch (Exception e) {
        log.error("Failed to fetch fun fact: {}", e.getMessage());
    }
    return "";
}
}