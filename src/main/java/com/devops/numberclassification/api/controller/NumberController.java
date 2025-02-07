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
        double numDouble = Double.parseDouble(trimmedNumber); // Parse as double
        int num = (int) numDouble; // Truncate decimal part (e.g., 3.14 → 3)
        return ResponseEntity.ok(numberService.classifyNumber(num));
    } catch (NumberFormatException e) {
        return ResponseEntity.badRequest().body(
            ErrorResponse.builder().number(number).build()
        );
    }
}
}