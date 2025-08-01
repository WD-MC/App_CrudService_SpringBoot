package com.example.registration.employee.equipment.enums;

import com.example.registration.employee.equipment.exception.InvalidEquipmentStatusException;

public enum StatutEquipment {
    DISPONIBLE("disponible"),
    ATTRIBUE("attribue"),
    EN_MAINTENANCE("en_maintenance");

    private final String value;

    StatutEquipment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StatutEquipment fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidEquipmentStatusException("Le statut ne peut pas être vide");
        }

        for (StatutEquipment statut : StatutEquipment.values()) {
            if (statut.value.equalsIgnoreCase(value.trim())) {
                return statut;
            }
        }
        throw new InvalidEquipmentStatusException(value);
    }
}
/*public enum StatutEquipment {

    DISPONIBLE("disponible"),
    ATTRIBUE("attribue"),
    EN_MAINTENANCE("en_maintenance");

    private final String value;

    StatutEquipment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /*public static StatutEquipment fromValue(String value){
        for (StatutEquipment statut : StatutEquipment.values()){
            if (statut.value.equalsIgnoreCase(value)){
                return statut;
            }
        }
        throw new IllegalArgumentException("Statut non valide: " + value);
    }*/
//}
