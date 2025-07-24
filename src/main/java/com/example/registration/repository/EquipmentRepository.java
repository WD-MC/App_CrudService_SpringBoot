package com.example.registration.repository;

import com.example.registration.entities.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Integer> {
}
