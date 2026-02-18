package com.sodemed.services.medicationorders.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodemed.dtos.medicationorders.response.DtoMedicalOrderRequestMovement;
import com.sodemed.exceptions.NotCreateException;
import com.sodemed.exceptions.NotFoundException;
import com.sodemed.models.medicationorders.MedicalOrderRequestMovement;
import com.sodemed.repositories.medicationorders.MedicalOrderRequestMovementRepository;
import com.sodemed.services.medicationorders.MedicalOrderRequestMovementService;
import com.sodemed.utils.medicationorders.MedicalOrderRequestMovementMapper;

@Service
public class MedicalOrderRequestMovementServiceImpl implements MedicalOrderRequestMovementService {

    @Autowired
    private MedicalOrderRequestMovementRepository medicalOrderRequestMovementRepository;

    @Override
    public DtoMedicalOrderRequestMovement create(MedicalOrderRequestMovement medicalOrderRequestMovement) {
        try {
            MedicalOrderRequestMovement medicalOrderRequestMovementSave = this.medicalOrderRequestMovementRepository
                    .save(medicalOrderRequestMovement);
            return MedicalOrderRequestMovementMapper.toDto(medicalOrderRequestMovementSave);
        } catch (Exception e) {
            throw new NotCreateException("Gestión de solicitud no guardada");
        }
    }

    @Override
    public List<DtoMedicalOrderRequestMovement> fetch(long orderId) {
        try {
            List<MedicalOrderRequestMovement> movements = this.medicalOrderRequestMovementRepository
                    .findByMedicationOrder(orderId);
            List<DtoMedicalOrderRequestMovement> dtoMovements = movements.stream()
                    .map(MedicalOrderRequestMovementMapper::toDto)
                    .collect(Collectors.toList());
            return dtoMovements;
        } catch (Exception ex) {
            throw new NotFoundException("No se encontró historial de movimientos");
        }
    }

}
