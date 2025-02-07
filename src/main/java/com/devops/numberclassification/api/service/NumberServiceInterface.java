package com.devops.numberclassification.api.service;

import com.devops.numberclassification.api.dto.response.NumberResponse;

public interface NumberServiceInterface {
    NumberResponse classifyNumber(double number);
}