package com.example.registration.employee.equipment.controller;

import com.example.registration.employee.equipment.dto.Equipment;
import com.example.registration.employee.equipment.dto.EquipmentRequest;
import com.example.registration.employee.equipment.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipmentController implements EquipmentsApi {

    private final EquipmentService service;

    @Autowired
    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Equipment>> equipmentsGet() {
        List<Equipment> equipments = service.getAll();
        return ResponseEntity.ok(equipments);
    }

    @Override
    public ResponseEntity<Equipment> equipmentsPost(EquipmentRequest request) {
        Equipment createdEquipment = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipment);
    }

    @Override
    public ResponseEntity<Equipment> equipmentsIdGet(Integer id) {
        Equipment equipment = service.getById(id);
        return ResponseEntity.ok(equipment);
    }

    @Override
    public ResponseEntity<Equipment> equipmentsIdPut(Integer id, EquipmentRequest request) {
        Equipment updatedEquipment = service.update(id, request);
        return ResponseEntity.ok(updatedEquipment);
    }

    @Override
    public ResponseEntity<Void> equipmentsIdDelete(Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}