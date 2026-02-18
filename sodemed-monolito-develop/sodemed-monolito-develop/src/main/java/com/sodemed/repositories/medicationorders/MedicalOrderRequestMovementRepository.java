package com.sodemed.repositories.medicationorders;

import com.sodemed.models.medicationorders.MedicalOrderRequestMovement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalOrderRequestMovementRepository extends JpaRepository<MedicalOrderRequestMovement, Long> {

    List<MedicalOrderRequestMovement> findByMedicationOrder(long medicationOrder);
}
