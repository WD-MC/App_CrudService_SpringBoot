package com.example.registration.employee.equipment.exception;

public class InvalidEquipmentStatusException extends RuntimeException {
    public InvalidEquipmentStatusException(String status) {
        super("Statut d'équipement invalide: " + status);
    }

}

