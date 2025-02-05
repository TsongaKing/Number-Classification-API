/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devops.numberclassification.api.service;

import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.util.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Slf4j
@Service
public class NumberServiceImpl implements NumberServiceInterface {

    private final RestTemplate restTemplate;

    public NumberServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public NumberResponse classifyNumber(int number) {
        return NumberResponse.builder()
            .number(number)
            .isPrime(NumberUtils.isPrime(number))
            .isPerfect(NumberUtils.isPerfect(number))
            .properties(NumberUtils.getProperties(number))
            .digitSum(NumberUtils.digitSum(number))
            .funFact(fetchFunFact(number))
            .build();
    }

    private String fetchFunFact(int number) {
        try {
            String url = "http://numbersapi.com/" + number + "/math?json";
            var response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().get("text").toString();
            }
        } catch (Exception e) {
            log.error("Failed to fetch fun fact: {}", e.getMessage());
        }
        return ""; // Empty string on failure
    }
}