package com.sodemed.repositories.users;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sodemed.models.users.Beneficiary;
import com.sodemed.models.users.enums.StatusUser;


public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    boolean existsByEmail(String email);

    Optional<Beneficiary> findByEmail(String email);

    Optional<Beneficiary> findByIdentification(String identification);

    boolean existsByIdentification(String identification);

    Optional<Beneficiary> findByIdentificationAndStatus(String identification, StatusUser status);

    List<Beneficiary> findByContizing_Identification(String identification);

}
