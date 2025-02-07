
package com.devops.numberclassification.api.service;

import lombok.extern.slf4j.Slf4j; 
import com.devops.numberclassification.api.dto.response.NumberResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j 
@Service
public class NumberService {

    private final RestTemplate restTemplate;

    public NumberService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NumberResponse classifyNumber(int number) {
        return NumberResponse.builder()
            .number(number)
            .isPrime(isPrime(number))
            .isPerfect(isPerfect(number))
            .properties(classifyProperties(number))
            .digitSum(calculateDigitSum(number))
            .funFact(generateFunFact(number))
            .build();
    }

    private List<String> classifyProperties(int number) {
        List<String> properties = new ArrayList<>();
        if (isArmstrong(number)) {
            properties.add("armstrong");
        }
        if (number % 2 == 0) {
            properties.add("even");
        } else {
            properties.add("odd");
        }
        return properties;
    }

    private boolean isArmstrong(int number) {
        int original = Math.abs(number);
        int digits = String.valueOf(original).length();
        int sum = 0;
        int temp = original;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        return sum == original;
    }

    private int calculateDigitSum(int number) {
        int absoluteNumber = Math.abs(number);
        int sum = 0;
        while (absoluteNumber > 0) {
            sum += absoluteNumber % 10;
            absoluteNumber /= 10;
        }
        return sum;
    }

    private String generateFunFact(int number) {
        if (isArmstrong(number)) {
            String digits = String.valueOf(Math.abs(number)).replace("", " ");
            digits = digits.trim().replace(" ", "^" + String.valueOf(number).length() + " + ");
            return number + " is an Armstrong number because " + digits + " = " + Math.abs(number);
        }
        try {
            String url = "http://numbersapi.com/" + number + "/math?json";
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().get("text").toString();
            }
        } catch (Exception e) {
            log.error("Failed to fetch fun fact: {}", e.getMessage());
        }
        return "";
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private boolean isPerfect(int number) {
        if (number <= 1) return false;
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                sum += i;
                int complement = number / i;
                if (complement != i) sum += complement;
            }
        }
        return sum == number;
    }
}