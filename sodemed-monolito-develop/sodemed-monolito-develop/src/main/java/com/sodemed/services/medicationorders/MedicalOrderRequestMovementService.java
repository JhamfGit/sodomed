package com.sodemed.services.medicationorders;

import java.util.List;

import com.sodemed.dtos.medicationorders.response.DtoMedicalOrderRequestMovement;
import com.sodemed.models.medicationorders.MedicalOrderRequestMovement;

public interface MedicalOrderRequestMovementService {

    DtoMedicalOrderRequestMovement create(MedicalOrderRequestMovement medicalOrderRequestMovement);

    List<DtoMedicalOrderRequestMovement> fetch(long orderId);

}
