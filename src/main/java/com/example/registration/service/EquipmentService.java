package com.example.registration.service;

import com.example.registration.dto.Equipment;
import com.example.registration.dto.EquipmentRequest;

import java.util.List;

public interface EquipmentService {
    List<Equipment> getAll();
    Equipment getById(Integer id);
    Equipment create(EquipmentRequest request);
    Equipment update(Integer id, EquipmentRequest request);
    void delete (Integer id);
}
