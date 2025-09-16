package com.example.registration.employee.equipment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Equipment
 */

public record Equipment(
        @Schema(name = "id")
        Integer id,

        @Schema(name = "name")
        String name,

        @Schema(name = "brand")
        String brand,

        @Schema(name = "quantity")
        Integer quantity,

        @Schema(name = "employee")
        String employee,

        @Schema(name = "status")
        String status
) {}

