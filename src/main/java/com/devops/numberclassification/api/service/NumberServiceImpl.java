package com.devops.numberclassification.api.service;

import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.util.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

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
            .number(number)
            .isInteger(isInteger);

        if (isInteger) {
            builder.isPrime(NumberUtils.isPrime(integerValue))
                   .isPerfect(NumberUtils.isPerfect(integerValue))
                   .properties(NumberUtils.getProperties(integerValue))
                   .digitSum(NumberUtils.digitSum(integerValue))
                   .funFact(fetchFunFact(integerValue));
        } else {
            builder.isPrime(null)
                   .isPerfect(null)
                   .properties(List.of("non-integer"))
                   .digitSum(null)
                   .funFact("Non-integer numbers cannot be classified as prime, perfect, or Armstrong.");
        }

        return builder.build();
    }

    private String fetchFunFact(int number) {
        if (NumberUtils.isArmstrong(number)) {
            String digits = String.valueOf(number).replace("", " ").trim();
            int length = digits.split(" ").length;
            String formula = digits.replace(" ", "^" + length + " + ") + "^" + length + " = " + number;
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