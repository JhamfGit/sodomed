package com.sodemed.utils.medicationorders;

import com.sodemed.dtos.medicationorders.response.DtoMedicalOrderRequestMovement;
import com.sodemed.models.medicationorders.MedicalOrderRequestMovement;

public class MedicalOrderRequestMovementMapper {

    public static MedicalOrderRequestMovement toEntity(DtoMedicalOrderRequestMovement dtoMedicalOrderRequestMovement) {
        MedicalOrderRequestMovement medicalOrderRequestMovement = new MedicalOrderRequestMovement();
        medicalOrderRequestMovement.setStatusOrder(dtoMedicalOrderRequestMovement.getStatusOrder());
        medicalOrderRequestMovement.setIdEmployee(dtoMedicalOrderRequestMovement.getIdEmployee());
        medicalOrderRequestMovement.setNameEmployee(dtoMedicalOrderRequestMovement.getNameEmployee());
        medicalOrderRequestMovement.setMedicationOrder(dtoMedicalOrderRequestMovement.getMedicationOrder());
        medicalOrderRequestMovement.setEffDate(dtoMedicalOrderRequestMovement.getEffDate());
        medicalOrderRequestMovement.setObservations(dtoMedicalOrderRequestMovement.getObservations());
        return medicalOrderRequestMovement;
    }

    public static DtoMedicalOrderRequestMovement toDto(MedicalOrderRequestMovement medicalOrderRequestMovement) {
        DtoMedicalOrderRequestMovement dtoMedicalOrderRequestMovement = new DtoMedicalOrderRequestMovement();
        dtoMedicalOrderRequestMovement.setId(medicalOrderRequestMovement.getId());
        dtoMedicalOrderRequestMovement.setStatusOrder(medicalOrderRequestMovement.getStatusOrder());
        dtoMedicalOrderRequestMovement.setIdEmployee(medicalOrderRequestMovement.getIdEmployee());
        dtoMedicalOrderRequestMovement.setNameEmployee(medicalOrderRequestMovement.getNameEmployee());
        dtoMedicalOrderRequestMovement.setMedicationOrder(medicalOrderRequestMovement.getMedicationOrder());
        dtoMedicalOrderRequestMovement.setEffDate(medicalOrderRequestMovement.getEffDate());
        dtoMedicalOrderRequestMovement.setObservations(medicalOrderRequestMovement.getObservations());
        dtoMedicalOrderRequestMovement.setPartial(medicalOrderRequestMovement.isPartial());
        dtoMedicalOrderRequestMovement.setTotalPending(medicalOrderRequestMovement.getTotalPending());
        dtoMedicalOrderRequestMovement.setDetailsPending(medicalOrderRequestMovement.getDetailsPending());
        return dtoMedicalOrderRequestMovement;
    }

}
