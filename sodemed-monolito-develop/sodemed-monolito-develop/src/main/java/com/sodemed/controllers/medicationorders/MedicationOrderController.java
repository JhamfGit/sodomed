package com.sodemed.controllers.medicationorders;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sodemed.dtos.medicationorders.request.DtoRequestMedicationOrder;
import com.sodemed.dtos.medicationorders.request.RequestUpdate;
import com.sodemed.dtos.medicationorders.response.DtoMedicalOrderRequestMovement;
import com.sodemed.dtos.medicationorders.response.DtoResponseMedicationOrder;
import com.sodemed.models.medicationorders.enums.StatusOrder;
import com.sodemed.services.medicationorders.MedicalOrderRequestMovementService;
import com.sodemed.services.medicationorders.MedicationOrderService;
import com.sodemed.utils.response.ResponseData;
import com.sodemed.utils.response.ResponseDataList;
import com.sodemed.utils.response.ResponseDataPageable;

@RestController
@RequestMapping("/api/medication")
public class MedicationOrderController {

	@Autowired
	private MedicationOrderService medicationOrderService;
	@Autowired
	private MedicalOrderRequestMovementService medicalOrderRequestMovementService;

	@PostMapping(value = "/upload", consumes = { "multipart/form-data" })
	public ResponseData<DtoResponseMedicationOrder> uploadFiles(
			@RequestParam("files") List<MultipartFile> files,
			@RequestParam("data") String jsonData) {
		return new ResponseData<DtoResponseMedicationOrder>(HttpStatus.CREATED,
				this.medicationOrderService.beforeCreate(jsonData, files));
	}

	@PostMapping(value = "/", headers = ("content-type=multipart/*"))
	public ResponseData<DtoResponseMedicationOrder> create(
			@RequestPart("fileIdentification") MultipartFile fileIdentification,
			@RequestPart("fileMedicationOrder") MultipartFile fileMedicationOrder,
			@RequestPart("fileMedicalHistory") MultipartFile fileMedicalHistory,
			@RequestPart("medicationOrder") DtoRequestMedicationOrder dtoRequestMedicationOrder) {
		return new ResponseData<DtoResponseMedicationOrder>(HttpStatus.CREATED,
				this.medicationOrderService.create(dtoRequestMedicationOrder, fileIdentification, fileMedicationOrder,
						fileMedicalHistory));
	}

	@PutMapping(value = "/{id}")
	public ResponseData<DtoResponseMedicationOrder> update(@PathVariable(name = "id") long id,
			@RequestBody DtoRequestMedicationOrder dtoRequestMedicationOrder) {
		return new ResponseData<DtoResponseMedicationOrder>(HttpStatus.OK,
				this.medicationOrderService.update(id, dtoRequestMedicationOrder));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseData<DtoResponseMedicationOrder> delete(@PathVariable(name = "id") long id) {
		return new ResponseData<DtoResponseMedicationOrder>(HttpStatus.OK, this.medicationOrderService.delete(id));
	}

	@GetMapping(value = "/download-data")
	public ResponseDataList<DtoResponseMedicationOrder> getAll(
			@RequestParam(required = false) String start_date,
			@RequestParam(required = false) String end_date,
			@RequestParam(required = false) StatusOrder statusOrder,
			@RequestParam(required = false) String userIdentification) {
		return new ResponseDataList<DtoResponseMedicationOrder>(HttpStatus.OK,
				this.medicationOrderService.findAll(start_date, end_date, statusOrder, userIdentification));
	}

	@GetMapping(value = "/{idUserCreate}/pending")
	public ResponseDataList<DtoResponseMedicationOrder> getAllByUser(
			@PathVariable(name = "idUserCreate") long idUserCreate,
			@RequestParam(required = false) StatusOrder statusOrder) {
		return new ResponseDataList<DtoResponseMedicationOrder>(HttpStatus.OK,
				this.medicationOrderService.findAllByUser(idUserCreate, statusOrder));
	}

	@GetMapping(value = "/{id}")
	public ResponseData<DtoResponseMedicationOrder> fetchId(@PathVariable(name = "id") long id) {
		return new ResponseData<DtoResponseMedicationOrder>(HttpStatus.OK, this.medicationOrderService.findById(id));
	}

	@GetMapping(value = "/fetch")
	public ResponseDataPageable<DtoResponseMedicationOrder> fetchAllPageable(
			@RequestParam(required = false) String start_date,
			@RequestParam(required = false) String end_date,
			@RequestParam(required = false) StatusOrder statusOrder,
			@RequestParam(required = false) String userIdentification,
			@RequestParam int page,
			@RequestParam int size) {
		return new ResponseDataPageable<DtoResponseMedicationOrder>(HttpStatus.OK,
				this.medicationOrderService.findAllPageable(start_date, end_date, statusOrder, userIdentification, page,
						size));
	}

	@PutMapping(value = "/take/{id}")
	public ResponseData<DtoResponseMedicationOrder> update(@PathVariable(name = "id") long id,
			@RequestParam(name = "idEmployee") Long idEmployee,
			@RequestParam(name = "nameEmployee") String nameEmployee) {
		return new ResponseData<DtoResponseMedicationOrder>(HttpStatus.OK,
				this.medicationOrderService.takeMedicationOrder(id, idEmployee, nameEmployee));
	}

	@GetMapping(value = "/{idUserCreate}/history")
	public ResponseDataList<DtoResponseMedicationOrder> getAllByUserHistory(
			@PathVariable(name = "idUserCreate") long idUserCreate,
			@RequestParam(required = false) StatusOrder statusOrder) {
		return new ResponseDataList<DtoResponseMedicationOrder>(HttpStatus.OK,
				this.medicationOrderService.findAllByUserHistory(idUserCreate));
	}

	@PutMapping(value = "/partial/update/{id}")
	public ResponseData<DtoResponseMedicationOrder> updateMedicationOrder(@PathVariable(name = "id") long id,
			@RequestBody RequestUpdate request) {
		return new ResponseData<DtoResponseMedicationOrder>(HttpStatus.OK,
				this.medicationOrderService.partialUpdateMedicationOrder(id, request));
	}

	@GetMapping(value = "/movements/{id}")
	public ResponseDataList<DtoMedicalOrderRequestMovement> getMovements(@PathVariable(name = "id") long id) {
		return new ResponseDataList<DtoMedicalOrderRequestMovement>(HttpStatus.OK,
				this.medicalOrderRequestMovementService.fetch(id));
	}

	@RequestMapping(method = RequestMethod.GET, path = "/resource/{filename}")
	public ResponseEntity<Resource> getFile(@PathVariable(name = "filename", required = true) String filename,
			@RequestParam(name = "mediaLocation") String mediaLocation) throws IOException {
		return this.medicationOrderService.loadResource(filename, mediaLocation);
	}
}
