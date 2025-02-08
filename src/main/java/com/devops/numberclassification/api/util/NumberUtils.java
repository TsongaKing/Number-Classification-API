package com.devops.numberclassification.api.util;

import java.util.ArrayList;
import java.util.List;

public class NumberUtils {

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static boolean isPerfect(int number) {
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

    public static int digitSum(int number) {
        return String.valueOf(Math.abs(number))
                   .chars()
                   .map(Character::getNumericValue)
                   .sum();
    }

    public static boolean isArmstrong(int number) {
        int original = Math.abs(number);
        int digits = String.valueOf(original).length();
        int sum = 0;
        while (original > 0) {
            int digit = original % 10;
            sum += Math.pow(digit, digits);
            original /= 10;
        }
        return sum == Math.abs(number);
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}