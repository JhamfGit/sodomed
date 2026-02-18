package com.sodemed.services.users;

import org.springframework.data.domain.Page;

import com.sodemed.dtos.users.request.DtoRequestEmployee;
import com.sodemed.dtos.users.response.DtoEmployee;

public interface EmployeeService {
    DtoEmployee create(DtoRequestEmployee employee);

    DtoEmployee fetch(long id);

    DtoEmployee update(long id, DtoRequestEmployee employee);

    DtoEmployee delete(long id);

    DtoEmployee active(long id);

    Page<DtoEmployee> fetchAll(Long id, String identification, String name,
            String lastName, String email, int page, int size);
}
