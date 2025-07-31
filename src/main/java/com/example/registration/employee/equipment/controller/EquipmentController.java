package com.example.registration.employee.equipment.controller;

import com.example.registration.employee.equipment.dto.Equipment;
import com.example.registration.employee.equipment.dto.EquipmentRequest;
import com.example.registration.employee.equipment.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipmentController implements EquipmentsApi{
    private final EquipmentService service;

    @Autowired
    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Equipment>> equipmentsGet(){
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<Equipment> equipmentsPost(EquipmentRequest request){
        return ResponseEntity.status(200).body(service.create(request));
    }

    @Override
    public ResponseEntity<Equipment> equipmentsIdGet(Integer id){
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<Equipment> equipmentsIdPut(Integer id, EquipmentRequest request){
        return ResponseEntity.ok(service.update(id, request));
    }

    @Override
    public ResponseEntity<Void> equipmentsIdDelete(Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
