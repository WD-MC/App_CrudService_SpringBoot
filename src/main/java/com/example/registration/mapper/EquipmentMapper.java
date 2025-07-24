package com.example.registration.mapper;

import com.example.registration.dto.Equipment;
import com.example.registration.dto.EquipmentRequest;
import com.example.registration.entities.EquipmentEntity;
import com.example.registration.enums.StatutEquipment;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    public Equipment toDto(EquipmentEntity entity){
        return new Equipment()
                .id(entity.getId())
                .name(entity.getName())
                .brand(entity.getBrand())
                .quantity(entity.getQuantity())
                .employee(entity.getEmployee())
                .status(entity.getStatus().getValue());
    }

    public EquipmentEntity toEntity(EquipmentRequest dto){
        return EquipmentEntity.builder()
                .name(dto.getName())
                .brand(dto.getBrand())
                .quantity(dto.getQuantity())
                .employee(dto.getEmployee())
                .status(StatutEquipment.valueOf(dto.getStatus().toUpperCase()))
                .build();
    }

    public void updateEntityFromDto(EquipmentRequest dto, EquipmentEntity entity){
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setQuantity(dto.getQuantity());
        entity.setEmployee(dto.getEmployee());
        entity.setStatus(StatutEquipment.valueOf(dto.getStatus().toUpperCase()));
    }
}
