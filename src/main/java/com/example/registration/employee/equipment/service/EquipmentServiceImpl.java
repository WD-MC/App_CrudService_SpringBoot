package com.example.registration.employee.equipment.service;

import com.example.registration.employee.equipment.dto.Equipment;
import com.example.registration.employee.equipment.dto.EquipmentRequest;
import com.example.registration.employee.equipment.entities.EquipmentEntity;
import com.example.registration.employee.equipment.mapper.EquipmentMapper;
import com.example.registration.employee.equipment.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository repository;
    private final EquipmentMapper mapper;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository repository, EquipmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Equipment> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Equipment getById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Équipement non trouvé avec l'ID: " + id));
    }

    @Override
    public Equipment create(EquipmentRequest request) {
        EquipmentEntity entity = mapper.toEntity(request);
        EquipmentEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public Equipment update(Integer id, EquipmentRequest request) {
        EquipmentEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Équipement non trouvé avec l'ID: " + id));

        mapper.updateEntityFromDto(request, entity);
        EquipmentEntity updatedEntity = repository.save(entity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Équipement non trouvé avec l'ID: " + id);
        }
        repository.deleteById(id);
    }
}
