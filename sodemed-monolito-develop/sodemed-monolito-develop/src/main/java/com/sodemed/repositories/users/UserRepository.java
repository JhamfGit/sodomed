package com.sodemed.repositories.users;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sodemed.models.users.User;
import com.sodemed.models.users.enums.StatusUser;
import com.sodemed.models.users.enums.TypeIdentification;
import com.sodemed.models.users.enums.UserType;

public interface UserRepository extends JpaRepository<User, Long> {

        boolean existsByEmail(String email);

        boolean existsByIdentification(String identification);

        boolean existsByEmailAndUserType(String email, UserType userType);

        boolean existsByIdentificationAndUserType(String identification, UserType userType);

        Optional<User> findByEmail(String email);

        Optional<User> findByIdentification(String identification);

        Optional<User> findByIdentificationAndStatus(String identification, StatusUser status);

        Optional<User> findByIdentificationAndStatusAndUserType(String identification, StatusUser status,
                        UserType userType);

        Optional<User> findByLoginAndStatusAndTypeIdentification(String login, StatusUser status,
                        TypeIdentification typeIdentification);

        Optional<User> findByLoginAndStatus(String login, StatusUser status);

}
