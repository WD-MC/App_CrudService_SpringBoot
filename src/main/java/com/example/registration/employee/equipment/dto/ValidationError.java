package com.example.registration.employee.equipment.dto;

public record ValidationError(
        String field,
        String message,
        Object rejectedValue
) {}
