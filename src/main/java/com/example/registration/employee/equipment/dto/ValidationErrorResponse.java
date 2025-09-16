package com.example.registration.employee.equipment.dto;

import java.util.List;

public record ValidationErrorResponse(
        String message,
        String code,
        long timestamp,
        String path,
        List<ValidationError> validationErrors
) {}
