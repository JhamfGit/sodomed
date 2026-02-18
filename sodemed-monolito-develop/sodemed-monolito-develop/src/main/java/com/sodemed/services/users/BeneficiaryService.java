package com.sodemed.services.users;

import java.util.List;

import com.sodemed.dtos.users.request.DtoRequestBeneficiary;
import com.sodemed.dtos.users.response.DtoBeneficiary;

public interface BeneficiaryService {
    DtoBeneficiary create(DtoRequestBeneficiary beneficiary);
    DtoBeneficiary fetch(long id);
    DtoBeneficiary  update(long id, DtoRequestBeneficiary beneficiary);
    DtoBeneficiary delete(long id);
    DtoBeneficiary fetchByIdentificacion(String identification);
    List<DtoBeneficiary> fetchByIdentificacionContizing(String identificacionContizing);
}
