package com.sodemed.repositories.users;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sodemed.models.users.Client;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeIdentification;
import com.sodemed.models.users.enums.UserType;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByEmail(String email);

    boolean existsByIdentification(String identification);

    boolean existsByEmailAndUserType(String email, UserType userType);

    boolean existsByIdentificationAndUserType(String identification, UserType userType);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByIdentification(String identification);

    Optional<Client> findByIdentificationAndStatus(String identification, StatusUser status);

    Optional<Client> findByLoginAndStatusAndTypeIdentification(String login, StatusUser status,
            TypeIdentification typeIdentification);

}
