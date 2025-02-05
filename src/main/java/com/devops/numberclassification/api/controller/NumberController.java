package com.devops.numberclassification.api.controller;

import com.devops.numberclassification.api.dto.response.ErrorResponse;
import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.service.NumberServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NumberController {

    private final NumberServiceInterface numberService;

    public NumberController(NumberServiceInterface numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/classify-number")
public ResponseEntity<?> classifyNumber(@RequestParam String number) {
    try {
        int num = Integer.parseInt(number);
        NumberResponse response = numberService.classifyNumber(num);
        return ResponseEntity.ok(response);
    } catch (NumberFormatException e) {
        return ResponseEntity.badRequest().body(
            ErrorResponse.builder()
                .number(number) // Directly uses the invalid input from the request
                .build()
        );
    }
}
}