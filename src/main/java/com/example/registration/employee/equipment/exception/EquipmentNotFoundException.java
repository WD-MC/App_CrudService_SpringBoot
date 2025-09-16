package com.example.registration.employee.equipment.exception;

public class EquipmentNotFoundException extends RuntimeException{
    public EquipmentNotFoundException(String message) {
        super(message);
    }
    public EquipmentNotFoundException(Integer id) {
        super("Équipement non trouvé avec l'ID: " + id);
    }

}
