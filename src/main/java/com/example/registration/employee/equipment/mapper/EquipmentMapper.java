package com.example.registration.employee.equipment.mapper;

import com.example.registration.employee.equipment.dto.Equipment;
import com.example.registration.employee.equipment.dto.EquipmentRequest;
import com.example.registration.employee.equipment.entities.EquipmentEntity;
import com.example.registration.employee.equipment.enums.StatutEquipment;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    public Equipment toDto(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Equipment(
                entity.getId(),
                entity.getName(),
                entity.getBrand(),
                entity.getQuantity(),
                entity.getEmployee(),
                entity.getStatus() != null ? entity.getStatus().getValue() : null
        );
    }

    public EquipmentEntity toEntity(EquipmentRequest dto) {
        if (dto == null) {
            return null;
        }

        return EquipmentEntity.builder()
                .name(dto.name())
                .brand(dto.brand())
                .quantity(dto.quantity())
                .employee(dto.employee())
                .status(StatutEquipment.fromValue(dto.status()))
                .build();
    }

    public void updateEntityFromDto(EquipmentRequest dto, EquipmentEntity entity) {
        if (dto == null || entity == null) {
            throw new IllegalArgumentException("DTO et entité ne peuvent pas être null");
        }

        entity.setName(dto.name());
        entity.setBrand(dto.brand());
        entity.setQuantity(dto.quantity());
        entity.setEmployee(dto.employee());
        entity.setStatus(StatutEquipment.fromValue(dto.status()));
    }
}

