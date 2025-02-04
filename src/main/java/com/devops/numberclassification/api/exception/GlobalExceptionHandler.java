/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.devops.numberclassification.api.exception;

import com.devops.numberclassification.api.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleNumberFormatException(NumberFormatException ex) {
        String invalidInput = ex.getMessage().split(":")[1].trim(); // Extracting the actual input
        
        // Create error response based on new ErrorResponse structure
        ErrorResponse response = ErrorResponse.builder()
            .number(invalidInput)     // Use 'alphabet' if necessary for invalid input handling
            .error(true)              // error set to true as per your requirement
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // Create generic error response
        ErrorResponse response = ErrorResponse.builder()
            .number("alphabet")      // Default number value
            .error(true)              // error set to true for generic errors
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
