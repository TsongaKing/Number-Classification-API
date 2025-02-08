package com.devops.numberclassification.api.service;

import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.util.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Slf4j
@Service
public class NumberService {

    private final RestTemplate restTemplate;

    public NumberService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NumberResponse classifyNumber(double number) {
        boolean isInteger = (number == Math.floor(number)) && !Double.isInfinite(number);
        int integerValue = isInteger ? (int) number : 0;

        return NumberResponse.builder()
            .number(number)
            .isPrime(isInteger ? NumberUtils.isPrime(integerValue) : null)
            .isPerfect(isInteger ? NumberUtils.isPerfect(integerValue) : null)
            .properties(getProperties(number, isInteger, integerValue))
            .digitSum(isInteger ? NumberUtils.digitSum(integerValue) : null)
            .funFact(generateFunFact(number, isInteger, integerValue))
            .build();
    }

    private List<String> getProperties(double number, boolean isInteger, int integerValue) {
        List<String> properties = new ArrayList<>();
        
        if (isInteger) {
            // Sign property
            properties.add(integerValue < 0 ? "negative" : "positive");
            
            // Even/odd
            properties.add(NumberUtils.isEven(integerValue) ? "even" : "odd");
            
            // Special numbers
            if (NumberUtils.isArmstrong(integerValue)) {
                properties.add("armstrong");
            }
        } else {
            properties.add("non-integer");
        }
        return properties;
    }

    private String generateFunFact(double number, boolean isInteger, int integerValue) {
        if (!isInteger) {
            return "Non-integer numbers cannot be classified as prime, perfect, or Armstrong.";
        }
        
        if (NumberUtils.isArmstrong(integerValue)) {
            String digits = String.valueOf(Math.abs(integerValue)).replace("", " ");
            digits = digits.trim().replace(" ", "^" + String.valueOf(integerValue).length() + " + ");
            return integerValue + " is an Armstrong number because " + digits + " = " + Math.abs(integerValue);
        }
        
        try {
            String url = "http://numbersapi.com/" + integerValue + "/math?json";
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().get("text").toString();
            }
        } catch (Exception e) {
            log.error("Failed to fetch fun fact: {}", e.getMessage());
        }
        return "";
    }
}