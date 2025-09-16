package com.example.registration.employee.equipment.service;

import com.example.registration.employee.equipment.dto.Equipment;
import com.example.registration.employee.equipment.dto.EquipmentRequest;
import com.example.registration.employee.equipment.entities.EquipmentEntity;
import com.example.registration.employee.equipment.exception.EquipmentBusinessException;
import com.example.registration.employee.equipment.exception.EquipmentNotFoundException;
import com.example.registration.employee.equipment.mapper.EquipmentMapper;
import com.example.registration.employee.equipment.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository repository;
    private final EquipmentMapper mapper;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository repository, EquipmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipment> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Equipment getById(Integer id) {
        if (id == null || id <= 0) {
            throw new EquipmentBusinessException("L'ID de l'équipement doit être un entier positif");
        }

        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
    }

    @Override
    public Equipment create(EquipmentRequest request) {

        validateEquipmentRequest(request);

        EquipmentEntity entity = mapper.toEntity(request);
        EquipmentEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public Equipment update(Integer id, EquipmentRequest request) {
        if (id == null || id <= 0) {
            throw new EquipmentBusinessException("L'ID de l'équipement doit être un entier positif");
        }

        validateEquipmentRequest(request);

        EquipmentEntity entity = repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));

        mapper.updateEntityFromDto(request, entity);
        EquipmentEntity updatedEntity = repository.save(entity);
        return mapper.toDto(updatedEntity);
    }

    @Override
    public void delete(Integer id) {
        if (id == null || id <= 0) {
            throw new EquipmentBusinessException("L'ID de l'équipement doit être un entier positif");
        }

        if (!repository.existsById(id)) {
            throw new EquipmentNotFoundException(id);
        }

        repository.deleteById(id);
    }

    private void validateEquipmentRequest(EquipmentRequest request) {

        if (request.quantity() <= 0) {
            throw new EquipmentBusinessException("La quantité doit être supérieure à zéro");
        }

        if (request.employee().trim().length() < 3) {
            throw new EquipmentBusinessException("Le nom de l'employé doit contenir au moins 3 caractères");
        }

        if (request.brand().trim().length() < 3) {
            throw new EquipmentBusinessException("La marque doit contenir au moins 3 caractères");
        }
    }
}