package com.example.registration.employee.equipment.repository;

import com.example.registration.employee.equipment.entities.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Integer> {
}
