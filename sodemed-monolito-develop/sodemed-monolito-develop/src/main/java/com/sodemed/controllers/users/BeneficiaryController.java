package com.sodemed.controllers.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sodemed.dtos.users.request.DtoRequestBeneficiary;
import com.sodemed.dtos.users.response.DtoBeneficiary;
import com.sodemed.services.users.BeneficiaryService;
import com.sodemed.utils.response.ResponseData;
import com.sodemed.utils.response.ResponseDataList;

@RestController
@RequestMapping("/api/beneficiary")
public class BeneficiaryController {
    @Autowired
    private BeneficiaryService beneficiaryService;

    @PostMapping(value = "/")
    public ResponseData<DtoBeneficiary> createBeneficiary(@RequestBody DtoRequestBeneficiary beneficiary) {
        return new ResponseData<DtoBeneficiary>(HttpStatus.CREATED,this.beneficiaryService.create(beneficiary));
    }

    @GetMapping(value = "/{id}")
    public ResponseData<DtoBeneficiary> fetchBeneficiary(@PathVariable(name = "id") long id) {
        return new ResponseData<DtoBeneficiary>(HttpStatus.OK,this.beneficiaryService.fetch(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseData<DtoBeneficiary> updateBeneficiary(@PathVariable(name = "id")  long id, @RequestBody  DtoRequestBeneficiary beneficiary) {
        return new ResponseData<DtoBeneficiary>(HttpStatus.OK,this.beneficiaryService.update(id, beneficiary));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseData<DtoBeneficiary> deleteBeneficiary(@PathVariable(name = "id") long id) {
        return new ResponseData<DtoBeneficiary>(HttpStatus.OK,this.beneficiaryService.delete(id));
    }

    @GetMapping(value = "/by-client")
    public ResponseDataList<DtoBeneficiary> getBeneficiariesByClient(@RequestParam(name = "identificationClient") String identificationClient) {
        return new ResponseDataList<DtoBeneficiary>(HttpStatus.OK,this.beneficiaryService.fetchByIdentificacionContizing(identificationClient));
    }


}
