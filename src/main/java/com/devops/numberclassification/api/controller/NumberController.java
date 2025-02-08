package com.devops.numberclassification.api.controller;

import lombok.extern.slf4j.Slf4j;
import com.devops.numberclassification.api.dto.response.ErrorResponse;
import com.devops.numberclassification.api.dto.response.NumberResponse;
import com.devops.numberclassification.api.service.NumberServiceInterface;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class NumberController {

    private final NumberServiceInterface numberService;

    public NumberController(NumberServiceInterface numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/classify-number")
public ResponseEntity<?> classifyNumber(
    @RequestParam(name = "number") String numberParam) {
    
    try {
        // Handle empty input
        if (numberParam == null || numberParam.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                .body(ErrorResponse.builder().number("empty_input").build());
        }

        String trimmedNumber = numberParam.trim();
        double parsedNumber = Double.parseDouble(trimmedNumber);
        
        NumberResponse response = numberService.classifyNumber(parsedNumber);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);

    } catch (NumberFormatException e) {
        // Handle non-numeric input
        return ResponseEntity.badRequest()
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorResponse.builder().number(numberParam).build());
            
    } catch (Exception e) {
        // Handle unexpected errors
        log.error("Unexpected error processing request", e);
        return ResponseEntity.internalServerError()
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorResponse.builder().number("server_error").build());
    }
}
}