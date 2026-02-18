package com.sodemed.repositories.users;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sodemed.models.users.Role;
import com.sodemed.models.users.enums.StatusRole;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByCode(String code);
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
    Optional<Role> findByCode(String code);
    Optional<Role> findByIdAndStatus(long id, StatusRole status);
    List<Role> findByStatus(StatusRole status);

}
