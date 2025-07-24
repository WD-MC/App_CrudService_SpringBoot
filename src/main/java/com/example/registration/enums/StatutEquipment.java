package com.example.registration.enums;

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

    public static StatutEquipment fromValue(String value){
        for (StatutEquipment statut : StatutEquipment.values()){
            if (statut.value.equalsIgnoreCase(value)){
                return statut;
            }
        }
        throw new IllegalArgumentException("Statut non valide: " + value);
    }
}
