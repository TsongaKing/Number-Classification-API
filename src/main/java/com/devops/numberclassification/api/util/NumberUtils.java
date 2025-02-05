/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
        int absoluteNumber = Math.abs(number);
        int sum = 0;
        while (absoluteNumber > 0) {
            sum += absoluteNumber % 10;
            absoluteNumber /= 10;
        }
        return sum;
    }

    public static boolean isArmstrong(int number) {
        int original = number;
        int digits = String.valueOf(original).length();
        int sum = 0;
        while (original > 0) {
            int digit = original % 10;
            sum += Math.pow(digit, digits);
            original /= 10;
        }
        return sum == number;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static List<String> getProperties(int number) {
        List<String> properties = new ArrayList<>();
        if (isArmstrong(number)) properties.add("armstrong");
        properties.add(isEven(number) ? "even" : "odd");
        return properties;
    }
}