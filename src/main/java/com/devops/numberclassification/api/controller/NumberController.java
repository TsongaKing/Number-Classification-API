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
        String trimmedNumber = number.trim();
        double parsedNumber = Double.parseDouble(trimmedNumber); // Parse as double
        
        // Return 200 OK for all numeric inputs (including floating-point)
        return ResponseEntity.ok(numberService.classifyNumber(parsedNumber));
    } catch (NumberFormatException e) {
        // Return 400 Bad Request only for non-numeric inputs (e.g., "abc")
        return ResponseEntity.badRequest().body(
            ErrorResponse.builder().number(number).build()
        );
    }
}
}