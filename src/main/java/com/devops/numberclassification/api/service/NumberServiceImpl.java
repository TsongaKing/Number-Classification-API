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

        return NumberResponse.builder()
    .number(isInteger ? integerValue : number) // Now matches @JsonProperty
    .isPrime(isInteger ? NumberUtils.isPrime(integerValue) : null)
    .isPerfect(isInteger ? NumberUtils.isPerfect(integerValue) : null)
    .properties(buildProperties(integerValue, isInteger))
    .digitSum(isInteger ? NumberUtils.digitSum(integerValue) : null)
    .funFact(isInteger ? fetchFunFact(integerValue) : "Non-integer numbers cannot be classified as prime, perfect, or Armstrong.")
    .build();
    }

    private Number formatNumber(double number, boolean isInteger) {
        return isInteger ? (int) number : number;
    }

   
private List<String> buildProperties(int number, boolean isInteger) {
    if (!isInteger) return List.of("non-integer");
    
    List<String> props = new ArrayList<>();
    props.add(number < 0 ? "negative" : "positive");
    props.add(NumberUtils.isEven(number) ? "even" : "odd");
    
    if (NumberUtils.isArmstrong(Math.abs(number))) {
        props.add("armstrong");
    }
    
    return props;
}
    private String fetchFunFact(int number) {
        if (NumberUtils.isArmstrong(number)) {
            String digits = String.valueOf(Math.abs(number));
            String formula = digits.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(d -> d + "^" + digits.length())
                .reduce((a, b) -> a + " + " + b)
                .orElse("");
            return number + " is an Armstrong number because " + formula + " = " + Math.abs(number);
        }

        try {
            String url = "http://numbersapi.com/" + number + "/math?json";
            var response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody().get("text").toString();
            }
        } catch (Exception e) {
            log.error("Fun fact API error: {}", e.getMessage());
        }
        return "";
    }
}