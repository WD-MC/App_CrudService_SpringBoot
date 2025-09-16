package com.example.registration.employee.equipment.dto;


import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * EquipmentRequest
 */

public record EquipmentRequest(
        @NotBlank(message = "Le nom de l'équipement est obligatoire")
        @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,

        @NotBlank(message = "La marque est obligatoire")
        @Schema(name = "brand", requiredMode = Schema.RequiredMode.REQUIRED)
        String brand,

        @NotNull(message = "La quantité est obligatoire")
        @Positive(message = "La quantité doit être superieur a 1")
        @Schema(name = "quantity", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer quantity,

        @NotBlank(message = "L'employé assigné est obligatoire")
        @Schema(name = "employee", requiredMode = Schema.RequiredMode.REQUIRED)
        String employee,

        @NotBlank(message = "Le statut est obligatoire")
        @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
        String status
) {}

