package com.sodemed.services.medicationorders;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.sodemed.dtos.medicationorders.request.DtoRequestMedicationOrder;
import com.sodemed.dtos.medicationorders.request.RequestUpdate;
import com.sodemed.dtos.medicationorders.response.DtoResponseMedicationOrder;
import com.sodemed.models.medicationorders.enums.StatusOrder;

public interface MedicationOrderService {

    DtoResponseMedicationOrder beforeCreate(String jsonDtoMedicationOrder, List<MultipartFile> files);

    DtoResponseMedicationOrder create(DtoRequestMedicationOrder requestMedicationOrder,
            MultipartFile fileIdentification, MultipartFile fileMedicationOrder,
            MultipartFile fileMedicalHistory);

    Map<String, String> afterCreate(List<MultipartFile> files, String prefix);

    DtoResponseMedicationOrder update(long id, DtoRequestMedicationOrder dtoRequestMedicationOrder);

    List<DtoResponseMedicationOrder> findAll(String start_date, String end_Date, StatusOrder statusOrder,
            String identification);

    List<DtoResponseMedicationOrder> findAllByUser(long idUserCreate, StatusOrder statusUser);

    DtoResponseMedicationOrder findById(long id);

    DtoResponseMedicationOrder delete(long id);

    Page<DtoResponseMedicationOrder> findAllPageable(String start_date, String end_Date, StatusOrder statusOrder,
            String identification, int page, int size);

    DtoResponseMedicationOrder takeMedicationOrder(long id, Long idEmployee, String nameEmployee);

    DtoResponseMedicationOrder updateWhitMovement(long id, DtoRequestMedicationOrder dtoRequestMedicationOrder,
            Long idEmployee, String nameEmployee);

    List<DtoResponseMedicationOrder> findAllByUserHistory(long idUserCreate);

    DtoResponseMedicationOrder partialUpdateMedicationOrder(long id, RequestUpdate requestUpdate);

    ResponseEntity<Resource> loadResource(String filename, String mediaLocation);

}
