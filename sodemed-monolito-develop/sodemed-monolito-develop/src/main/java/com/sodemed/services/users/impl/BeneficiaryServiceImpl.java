package com.sodemed.services.users.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodemed.dtos.users.request.DtoRequestBeneficiary;
import com.sodemed.dtos.users.response.DtoBeneficiary;
import com.sodemed.exceptions.NotCreateException;
import com.sodemed.exceptions.NotFoundException;
import com.sodemed.exceptions.NotUpdateException;
import com.sodemed.models.users.Beneficiary;
import com.sodemed.models.users.Client;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.repositories.users.BeneficiaryRepository;
import com.sodemed.repositories.users.ClientRepository;
import com.sodemed.services.users.BeneficiaryService;
import com.sodemed.utils.users.EntityMapper;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public DtoBeneficiary create(DtoRequestBeneficiary beneficiary) {
        try {
            if (this.beneficiaryRepository.existsByIdentification(beneficiary.getIdentification())) {
                throw new Exception("La identificación ya se encuentra registrada");
            }
            Client client = clientRepository.findById(beneficiary.getClientId())
                    .orElseThrow(
                            () -> new NotFoundException("No se encontró usuario con ID " + beneficiary.getClientId()));
            Beneficiary beneficiarySave = EntityMapper.dtoRequestBeneficiaryToEntity(beneficiary);
            beneficiarySave.setContizing(client);
            beneficiarySave.setStatus(StatusUser.active);
            return EntityMapper.entityToDtoBeneficiary(this.beneficiaryRepository.save(beneficiarySave));
        } catch (Exception e) {
            throw new NotCreateException(e.getMessage());
        }
    }

    @Override
    public DtoBeneficiary fetch(long id) {
        try {
            Beneficiary beneficiary = this.beneficiaryRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró usuario con ID " + id));
            return EntityMapper.entityToDtoBeneficiary(beneficiary);
        } catch (Exception e) {
            throw new NotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public DtoBeneficiary update(long id, DtoRequestBeneficiary beneficiary) {
        try {
            Beneficiary beneficiaryExisting = this.beneficiaryRepository.findById(id)
                    .orElseThrow(() -> new NotUpdateException("No se encontró usuario con ID " + id));
            if (beneficiaryExisting != null
                    && !beneficiaryExisting.getIdentification().equals(beneficiary.getIdentification())) {
                if (this.beneficiaryRepository.existsByIdentification(beneficiary.getIdentification())) {
                    throw new NotUpdateException("La identificación ya se encuentra registrada");
                }
            }
            Beneficiary beneficiarySave = EntityMapper.dtoRequestBeneficiaryToEntity(beneficiary);
            beneficiarySave.setId(id);
            beneficiarySave.setContizing(beneficiaryExisting.getContizing());
            return EntityMapper.entityToDtoBeneficiary(this.beneficiaryRepository.save(beneficiarySave));
        } catch (NotUpdateException e) {
            throw new NotUpdateException(e.getMessage());
        } catch (Exception e) {
            throw new NotUpdateException("No se pudo actualizar el usuario con ID " + id);
        }
    }

    @Override
    public DtoBeneficiary delete(long id) {
        try {
            Beneficiary existingBeneficiary = this.beneficiaryRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró usuario con ID " + id));
            this.beneficiaryRepository.deleteById(id);
            existingBeneficiary.setStatus(StatusUser.inactive);
            return EntityMapper.entityToDtoBeneficiary(existingBeneficiary);
        } catch (Exception e) {
            throw new NotFoundException("No se pudo eliminar el usuario con ID " + id);
        }
    }

    @Override
    public DtoBeneficiary fetchByIdentificacion(String identificacion) {
        try {
            Beneficiary beneficiary = this.beneficiaryRepository.findByIdentification(identificacion)
                    .orElseThrow(() -> new NotFoundException(
                            "No se encontró usuario con identificación " + identificacion));
            return EntityMapper.entityToDtoBeneficiary(beneficiary);
        } catch (Exception e) {
            throw new NotFoundException("No se encontró usuario");
        }
    }

    @Override
    public List<DtoBeneficiary> fetchByIdentificacionContizing(String identificacionContizing) {
        try {
            List<Beneficiary> beneficiaries = this.beneficiaryRepository
                    .findByContizing_Identification(identificacionContizing);
            List<DtoBeneficiary> dtoBeneficiaries = beneficiaries.stream().map(EntityMapper::entityToDtoBeneficiary)
                    .collect(Collectors.toList());
            return dtoBeneficiaries;
        } catch (Exception e) {
            throw new NotFoundException("No se encontró usuarios");
        }
    }

}
