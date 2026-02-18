package com.sodemed.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sodemed.models.users.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
