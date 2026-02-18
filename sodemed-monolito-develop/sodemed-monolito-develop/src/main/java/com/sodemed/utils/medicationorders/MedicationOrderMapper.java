package com.sodemed.utils.medicationorders;

import com.sodemed.dtos.medicationorders.request.DtoRequestMedicationOrder;
import com.sodemed.dtos.medicationorders.response.DtoResponseMedicationOrder;
import com.sodemed.models.medicationorders.MedicationOrder;

public class MedicationOrderMapper {

    public static DtoResponseMedicationOrder entityToDtoMedicaltionOrder(MedicationOrder medicationOrder) {
        DtoResponseMedicationOrder dtoMedicationOrder = new DtoResponseMedicationOrder();
        dtoMedicationOrder.setId(medicationOrder.getId());
        dtoMedicationOrder.setIdMember(medicationOrder.getIdMember());
        dtoMedicationOrder.setCodigo(medicationOrder.getCodigo());
        dtoMedicationOrder.setUrlIdentificationFile(medicationOrder.getUrlIdentificationFile());
        dtoMedicationOrder.setUrlMedicationOrderFile(medicationOrder.getUrlMedicationOrderFile());
        dtoMedicationOrder.setUrlMedicalHistoryFile(medicationOrder.getUrlMedicalHistoryFile());
        dtoMedicationOrder.setComments(medicationOrder.getComments());
        dtoMedicationOrder.setHomeAddress(medicationOrder.getHomeAddress());
        dtoMedicationOrder.setHomeCity(medicationOrder.getHomeCity());
        dtoMedicationOrder.setUrlFiles(medicationOrder.getUrlFiles());
        dtoMedicationOrder.setUserOrderIdentification(medicationOrder.getUserOrderIdentification());
        dtoMedicationOrder.setUserOrderName(medicationOrder.getUserOrderName());
        dtoMedicationOrder.setTypeClient(medicationOrder.getTypeClient());
        dtoMedicationOrder.setCreationDate(medicationOrder.getCreationDate());
        dtoMedicationOrder.setDistrict(medicationOrder.getDistrict());
        dtoMedicationOrder.setFurtherDirectionIndication(medicationOrder.getFurtherDirectionIndication());
        dtoMedicationOrder.setStatusOrder(medicationOrder.getStatus());
        dtoMedicationOrder.setIdEmployee(medicationOrder.getIdEmployee());
        dtoMedicationOrder.setNameEmployee(medicationOrder.getNameEmployee());
        dtoMedicationOrder.setObservations(medicationOrder.getObservations());
        dtoMedicationOrder.setLastModificationDate(medicationOrder.getLastModificationDate());
        dtoMedicationOrder.setActive(medicationOrder.isActive());
        dtoMedicationOrder.setPartial(medicationOrder.isPartial());
        dtoMedicationOrder.setTotalPending(medicationOrder.getTotalPending());
        dtoMedicationOrder.setDetailsPending(medicationOrder.getDetailsPending());
        dtoMedicationOrder.setUserCreatePhone(medicationOrder.getUserCreate()!=null?medicationOrder.getUserCreate().getPhone():null);
        dtoMedicationOrder.setUserCreateEmail(medicationOrder.getUserCreate()!=null?medicationOrder.getUserCreate().getEmail():null);
        dtoMedicationOrder.setUserCreateCountryDialCode(medicationOrder.getUserCreate()!=null?medicationOrder.getUserCreate().getCountryDialCode():null);
        return dtoMedicationOrder;
    }

    public static MedicationOrder medicaltionOrderToEntity(DtoRequestMedicationOrder dtoRequestMedicationOrder) {
        MedicationOrder medicationOrder = new MedicationOrder();
        medicationOrder.setIdMember(dtoRequestMedicationOrder.getIdMember());
        medicationOrder.setCodigo(dtoRequestMedicationOrder.getCodigo());
        medicationOrder.setUrlIdentificationFile(dtoRequestMedicationOrder.getUrlIdentificationFile());
        medicationOrder.setUrlMedicationOrderFile(dtoRequestMedicationOrder.getUrlMedicationOrderFile());
        medicationOrder.setUrlMedicalHistoryFile(dtoRequestMedicationOrder.getUrlMedicalHistoryFile());
        medicationOrder.setComments(dtoRequestMedicationOrder.getComments());
        medicationOrder.setHomeAddress(dtoRequestMedicationOrder.getHomeAddress());
        medicationOrder.setHomeCity(dtoRequestMedicationOrder.getHomeCity());
        medicationOrder.setUrlFiles(dtoRequestMedicationOrder.getUrlFiles());
        medicationOrder.setUserOrderIdentification(dtoRequestMedicationOrder.getUserOrderIdentification());
        medicationOrder.setUserOrderName(dtoRequestMedicationOrder.getUserOrderName());
        medicationOrder.setTypeClient(dtoRequestMedicationOrder.getTypeClient());
        medicationOrder.setCreationDate(dtoRequestMedicationOrder.getCreationDate());
        medicationOrder.setDistrict(dtoRequestMedicationOrder.getDistrict());
        medicationOrder.setFurtherDirectionIndication(dtoRequestMedicationOrder.getFurtherDirectionIndication());
        return medicationOrder;
    }

}
