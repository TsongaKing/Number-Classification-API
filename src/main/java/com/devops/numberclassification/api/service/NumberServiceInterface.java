/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devops.numberclassification.api.service;

import com.devops.numberclassification.api.dto.response.NumberResponse;
import java.util.List;

public interface NumberServiceInterface {
    NumberResponse classifyNumber(int number);
    List<NumberResponse> classifyNumbers(List<Integer> numbers);
}

