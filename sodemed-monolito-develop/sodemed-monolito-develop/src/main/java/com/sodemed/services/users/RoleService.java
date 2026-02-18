package com.sodemed.services.users;

import java.util.List;

import com.sodemed.dtos.users.request.DtoRequestRole;
import com.sodemed.dtos.users.response.DtoRole;
import com.sodemed.models.users.enums.StatusRole;

public interface RoleService {
    DtoRole create(DtoRequestRole role);

    DtoRole update(long id, DtoRequestRole role);

    DtoRole delete(long id);

    List<DtoRole> fetchAll();

    List<String> getPermissions();

    List<DtoRole> fetchByStatus(StatusRole statusRole);
}
