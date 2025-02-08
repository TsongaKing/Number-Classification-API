package com.devops.numberclassification.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String number;
    @Builder.Default
    private boolean error = true;
}