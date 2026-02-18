package com.sodemed.services.users;

import java.util.List;

import com.sodemed.dtos.users.request.DtoRequestClient;
import com.sodemed.dtos.users.response.DtoClient;


public interface ClientService {
    DtoClient create(DtoRequestClient Client);
    DtoClient fetch(long id);
    DtoClient update(long id, DtoRequestClient Client);
    DtoClient delete(long id);
    List<DtoClient> fetchAll();
}
