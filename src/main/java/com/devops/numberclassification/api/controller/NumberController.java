package com.devops.numberclassification.api.controller;

import com.devops.numberclassification.api.dto.response.ErrorResponse;
import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.service.NumberServiceInterface;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class NumberController {

    private final NumberServiceInterface numberService;

    public NumberController(NumberServiceInterface numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/classify-number")
    public ResponseEntity<?> classifyNumber(@RequestParam String number) {
        try {
            String trimmedNumber = number.trim();
            double parsedNumber = Double.parseDouble(trimmedNumber);
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(numberService.classifyNumber(parsedNumber));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder().number(number).build());
        }
    }
}