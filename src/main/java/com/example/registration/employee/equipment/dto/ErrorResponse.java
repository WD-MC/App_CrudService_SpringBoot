package com.example.registration.employee.equipment.dto;

public record ErrorResponse(
        String message,
        String code,
        long timestamp,
        String path
) {}
