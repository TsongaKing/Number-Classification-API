package com.devops.numberclassification.api.controller;

import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.service.NumberServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/numbers")
public class NumberController {

    private final NumberServiceInterface numberService;

    public NumberController(NumberServiceInterface numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/classify/{number}")
    public ResponseEntity<?> classifyNumber(@PathVariable("number") Integer number) {
        try {
            NumberResponse response = numberService.classifyNumber(number);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Unexpected error: " + e.getMessage()));
        }
    }

    @PostMapping("/classify")
    public ResponseEntity<?> classifyNumbers(@RequestBody List<Integer> numbers) {
        try {
            List<NumberResponse> responses = numberService.classifyNumbers(numbers);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse("Unexpected error: " + e.getMessage()));
        }
    }

    private Map<String, Object> errorResponse(String message) {
        return Map.of("hasError", true, "message", message);
    }
}
