package com.sodemed.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodemed.dtos.users.request.DtoRequestClient;
import com.sodemed.dtos.users.response.DtoClient;
import com.sodemed.services.users.ClientService;
import com.sodemed.utils.response.ResponseData;
import com.sodemed.utils.response.ResponseDataList;


@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/")
    public ResponseData<DtoClient> createClient(@RequestBody DtoRequestClient Client) {
        return new ResponseData<DtoClient>(HttpStatus.CREATED, this.clientService.create(Client));
    }

    @GetMapping(value = "/{id}")
    public ResponseData<DtoClient> fetchClient(@PathVariable(name = "id") long id) {
        return new ResponseData<DtoClient>(HttpStatus.CREATED, this.clientService.fetch(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseData<DtoClient> updateClient(@PathVariable(name = "id") long id, @RequestBody  DtoRequestClient Client) {
        return new ResponseData<DtoClient>(HttpStatus.CREATED, this.clientService.update(id, Client));
    }

    @GetMapping(value = "/")
    public ResponseDataList<DtoClient> getAll() {
        return new ResponseDataList<DtoClient>(HttpStatus.OK, this.clientService.fetchAll());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseData<DtoClient> deleteClient(@PathVariable(name = "id") long id) {
        return new ResponseData<DtoClient>(HttpStatus.OK, this.clientService.delete(id));
    }
}
