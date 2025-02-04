/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.devops.numberclassification.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    @Builder.Default
    private Object number = "alphabet"; // Set default value as "alphabet"
    
    @Builder.Default
    private boolean error = true;       // Set 'error' as true
}


