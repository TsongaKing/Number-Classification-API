/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devops.numberclassification.api.service;

import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.util.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NumberServiceImpl implements NumberServiceInterface {

    private final RestTemplate restTemplate;

    public NumberServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public NumberResponse classifyNumber(int number) {
        boolean isPrime = NumberUtils.isPrime(number);
        boolean isPerfect = NumberUtils.isPerfect(number);
        List<String> properties = NumberUtils.getProperties(number);
        int digitSum = NumberUtils.digitSum(number);
        String funFact = getFunFact(number);

        return NumberResponse.builder()
                .number(number)
                .isPrime(isPrime)
                .isPerfect(isPerfect)
                .properties(properties)
                .digitSum(digitSum)
                .funFact(funFact)
                .build();
    }

    @Override
    public List<NumberResponse> classifyNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(this::classifyNumber)
                .collect(Collectors.toList());
    }

    private String getFunFact(int number) {
        String url = "http://numbersapi.com/" + number + "/math?json";
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().get("text").toString();
            }
        } catch (Exception e) {
            System.err.println("Error fetching fun fact: " + e.getMessage());
        }
        return "No fun fact available.";
    }
}
