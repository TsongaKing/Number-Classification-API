
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
    if (NumberUtils.isArmstrong(number)) {
        // Custom Armstrong fact
        String digits = String.valueOf(number).replace("", " ").trim(); // "3 7 1"
        int length = digits.split(" ").length;
        String formula = digits.replace(" ", "^" + length + " + ") 
                             + "^" + length + " = " + number;
        return number + " is an Armstrong number because " + formula;
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