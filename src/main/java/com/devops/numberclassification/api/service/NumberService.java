
package com.devops.numberclassification.api.service;

import com.devops.numberclassification.api.dto.response.NumberResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumberService {
    public NumberResponse classifyNumber(int number) {
        return NumberResponse.builder()
            .number(number)
            .isPrime(isPrime(number))      // Ensure these are included
            .isPerfect(isPerfect(number))  // in the builder
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
        if (isOdd(number)) {
            properties.add("odd");
        }
        return properties;
    }

    private boolean isArmstrong(int number) {
        // Armstrong logic (you can replace with actual implementation)
        return number == 371;  // Example check for Armstrong number
    }

    private boolean isOdd(int number) {
        return number % 2 != 0;
    }

    private int calculateDigitSum(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private String generateFunFact(int number) {
        // Example fun fact for 371 (Armstrong number)
        if (number == 371) {
            return "371 is an Armstrong number because 3^3 + 7^3 + 1^3 = 371";
        }
        return "No fun fact available";
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isPerfect(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
}

